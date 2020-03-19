JFLAGS=-implicit:none
JC=javac
JVM=java
APP_NAME=Arianne

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	
CLASSES= \
		SauvegarderJTextArea.java \
		NumberObject.java \
		Carre.java \
		Algorithme.java \
		Labyrinthe.java \
		ChoixAutomate.java \
		Main.java \
		Menu.java \
		Size.java \
		Grid.java \
		
MAIN = Main
default: compile

compile: $(CLASSES:.java=.class)


run: compile
	$(JVM) Main

clean: 
	$(RM) -f *.class
	$(RM) -f ${APP_NAME}.jar
