
javac pl/boardPieces/*.java pl/boardPieces/chessmans/*.java pl/communication/serializableMessage/*.java
jar -cvf MyLib.jar pl/boardPieces/*.class pl/boardPieces/chessmans/*.class pl/communication/serializableMessage/*.class

javac pl/communication/database/*.java pl/communication/serwer/*.java pl/communication/writerReader/*.java
jar -cvfm serwer.jar manifest2.txt pl/communication/database/*.class pl/communication/serwer/*.class pl/communication/writerReader/*.class

javac -classpath MyLib.jar pl/gui/*.java pl/communication/*.java pl/logic/management/*.java 
jar -cvfm client.jar manifest1.txt pl/gui/*.class pl/communication/*.class pl/logic/management/*.class pl/image/*.*