package compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
 
public class Compiler {
	
	public static final String DEFAULT_COMPILATION_DIRECTORY = "CompiledPrograms";
	
	public static final String DEFAULT_FRAME_DIRECTORY = "Frames";

    private static final String FILE_STUB =
            ".class public %s\n" +
                    ".super java/lang/Object\n" +
                    ".method public <init>()V\n" +
                    "   aload_0\n" +
                    "   invokenonvirtual java/lang/Object/<init>()V\n" +
                    "   return\n" +
                    ".end method\n" +
                    "\n" +
                    ".method public static main([Ljava/lang/String;)V\n" +
                    "       ; set limits used by this method\n" +
                    "       .limit locals  2\n" +
                    "       .limit stack 256\n" +
                    "\n" +
                    "       ;    1 - the PrintStream object held in java.lang.System.out\n" +
                    "       getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
                    "\n" +
                    "	new java/lang/Object\n" +
                    "	dup\n" +
                    "	invokespecial java/lang/Object/<init>()V\n" +
                    "	astore_1\n" +
                    "%s" +						//CODE
                    "\n" +
                    "       ; convert to String;\n" +
                    "       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n" +
                    "       ; call println \n" +
                    "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" +
                    "\n" +
                    "       return\n" +
                    "\n" +
                    ".end method";
    
    private final CodeBlock cb;
    
    private final String codeDirectory;
    
    private final String frameDirectory;
    
    public Compiler() {
    	codeDirectory = DEFAULT_COMPILATION_DIRECTORY;
    	frameDirectory = String.format("%s/%s", 
    			DEFAULT_COMPILATION_DIRECTORY, DEFAULT_FRAME_DIRECTORY);
    	new File(frameDirectory).mkdirs();
    	cb = new CodeBlock(frameDirectory);
    }
    
    private static void writeToFile(String filePath, String fileContent) throws IOException {
    	File f = new File(filePath);
    	try(FileWriter writer = new FileWriter(f, false)) {
    		writer.write(fileContent);
    	}
    	
    }
    
    public void generateOutputFile(String fileName) throws IOException {
        Collection<String> frameCode = cb.getFrameCode();
        int index = 0;
        for(String frame: frameCode) {
        	String framePath = String.format("%s/frame_%d.j", frameDirectory, index++);
        	writeToFile(framePath, frame);
        }
        
        String callStackCode = cb.getCallStackCode();
        String className = String.format("%s/%s", codeDirectory, fileName);
        String fileContent = String.format(FILE_STUB, className, callStackCode);
        String filePath = String.format("%s.j", className);
        writeToFile(filePath, fileContent);
    }
    
    public CodeBlock getCodeBlock() {
    	return cb;
    }

}
