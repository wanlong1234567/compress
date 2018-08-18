package info.javaperformance.gccheck.compress;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 * JDK java.util.zip Deflater/Inflater test
 */
public class JdkDeflateTest extends TestParent {
    @Param({"1", "2", "3", "4", "5", "6", "7", "8", "9"})
    public int m_lvl;

    @Benchmark
    public int deflate() throws IOException
    {
        return baseBenchmark(new StreamFactory() {
            @Override
            public OutputStream getStream(OutputStream underlyingStream) throws IOException {
                return new DeflaterOutputStream( underlyingStream, new Deflater( m_lvl, true ), 512 );
            }
        });
    }
}
