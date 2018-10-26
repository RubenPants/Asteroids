package asteroids.part2.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import asteroids.util.internal.ResourceUtils;

/**
 * Sound manager that reads a text file to determine which sound files to load.
 */
public class FileSoundManager implements Runnable, Sound {

	public abstract class Request {
		private final String sound;

		public Request(String sound) {
			this.sound = sound;
		}

		public String getSound() {
			return sound;
		}

		public abstract void execute();
	}

	public class Play extends Request {
		public Play(String sound) {
			super(sound);
		}

		@Override
		public void execute() {
			Clip clip = clips.get(getSound());
			if (clip == null) {
				System.err.println("clip " + getSound() + " not found");
			} else {
				if (clip.isRunning()) {
					clip.stop();
				}
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}

	public class Stop extends Request {
		public Stop(String sound) {
			super(sound);
		}

		@Override
		public void execute() {
			Clip clip = clips.get(getSound());
			if (clip == null) {
				System.err.println("clip " + getSound() + " not found");
			} else {
				clip.stop();
			}
		}
	}

	public class Loop extends Request {
		public Loop(String sound) {
			super(sound);
		}

		@Override
		public void execute() {
			Clip clip = clips.get(getSound());
			if (clip == null) {
				System.err.println("clip " + getSound() + " not found");
			} else {
				if (clip.isRunning()) {
					clip.stop();
				}
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
	}

	HashMap<String, Clip> clips = new HashMap<>();
	Queue<Request> requests = new LinkedList<>();

	public FileSoundManager(String path) {
		try {
			loadSounds(path);
		} catch (IOException e) {
			System.err.println("error loading sound description file");
		} catch (LineUnavailableException e) {
			System.err.println("no line available");
		} catch (UnsupportedAudioFileException e) {
			System.err.println("audio format not supported");
		} catch (IllegalArgumentException e) {
			System.err.println("system does not support at least one clip instance through any installed mixer");
		} catch (SecurityException e) {
			System.err.println("sound not available due to security restrictions");
		}
	}

	private void loadSounds(String path) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		if (AudioSystem.getMixerInfo().length == 0)
			throw new IllegalArgumentException("no mixer installed");
		ClassLoader loader = FileSoundManager.class.getClassLoader();
		InputStream stream = loader.getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			Clip clip = AudioSystem.getClip();
			URL url = ResourceUtils.toURL("asteroids/resources/" + line);
			if (url == null) {
				System.err.println("sound " + line + " not found");
				continue;
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			clip.open(audioStream);
			clips.put(line.substring(0, line.lastIndexOf('.')), clip);
		}
	}

	@Override
	public void play(String name) {
		addRequest(new Play(name));
	}

	@Override
	public void loop(String name) {
		addRequest(new Loop(name));
	}

	@Override
	public void stop(String name) {
		addRequest(new Stop(name));
	}

	public void addRequest(Request request) {
		synchronized (this) {
			requests.add(request);
			notifyAll();
		}
	}

	@Override
	public void run() {
		while (true) {
			Request request = null;
			synchronized (this) {
				while (request == null) {
					if (requests.isEmpty()) {
						try {
							wait();
						} catch (InterruptedException e) {
							return;
						}
					} else {
						request = requests.remove();
					}
				}
			}
			request.execute();
		}
	}

	@Override
	public void start() {
		new Thread(this).start();
	}
}
