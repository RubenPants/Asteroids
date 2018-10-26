#BINPATH=$(shell pwd)/../../../../../bin
BINPATH = $(shell pwd)/bin
ANTLRPATH = $(shell pwd)/../../../../../lib/antlr-4.7-complete.jar
CLASSPATH = ".:${BINPATH}:${ANTLRPATH}"



all: clean code

code:
	java -jar ${ANTLRPATH} \
             -o generated \
			 -visitor AsteroidsProgram.g4

build: code
	mkdir -p ${BINPATH} && javac -d ${BINPATH} -cp ${CLASSPATH} generated/*.java

test: build
	export CLASSPATH=${CLASSPATH}; \
	cd ../../../../; \
          for program in asteroids/resources/programs/*.txt; do \
            echo " --- $$program ---"; \
            java -cp ${CLASSPATH} 'org.antlr.v4.gui.TestRig' asteroids.part3.programs.internal.generated.AsteroidsProgram program <$$program; \
          done

clean:
	rm -rf generated && rm -rf ${BINPATH}
