package info.javaperformance.gccheck.compress;

import java.io.*;
import java.nio.file.Files;

/**
 * Create an input test file out of a local JDK docs directory (by concatenating all its files into one file)
 */
public class InputGenerator {
    private static final String JAVADOC_PATH = "C:/Java/jdk1.8.0/docs";
    public static final File FILE_PATH = new File( "C:/_site/javadoc" );

    static
    {
        try {
            if ( !FILE_PATH.exists() )
                makeJavadocFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeJavadocFile() throws IOException {
        try( OutputStream os = new BufferedOutputStream( new FileOutputStream( FILE_PATH ), 65536 ) )
        {
            appendDir(os, new File( JAVADOC_PATH ));
        }
        System.out.println( "Javadoc file created");
    }

    private static void appendDir( final OutputStream os, final File root ) throws IOException {
        for ( File f : root.listFiles() )
        {
            if ( f.isDirectory() )
                appendDir( os, f );
            else
                Files.copy(f.toPath(), os);
        }
    }
}
