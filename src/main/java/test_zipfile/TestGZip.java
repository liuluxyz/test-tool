package test_zipfile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipUtils;

/**
 * liulu5 2014-5-29
 */
public class TestGZip {

	/**
	 * @param targzFile
	 * @throws IOException
	 */
	public static void parseTARGZ(String targzFile) throws IOException {
		FileInputStream fileIn = null;
		BufferedInputStream bufIn = null;
		GZIPInputStream gzipIn = null;
		TarArchiveInputStream taris = null;
		try {
			fileIn = new FileInputStream(targzFile);
			bufIn = new BufferedInputStream(fileIn);
			gzipIn = new GZIPInputStream(bufIn); // first unzip the input file
			// stream
			taris = new TarArchiveInputStream(gzipIn);
			System.out.println(taris.getCount());
			
			TarArchiveEntry entry = null;
			while ((entry = taris.getNextTarEntry()) != null) {
				System.out.println(entry.isDirectory());
				System.out.println(entry.getName());
				
				
				File file = entry.getFile();
				if(file != null){
					System.out.println(file.getAbsolutePath());
					System.out.println(file.getName());				
				}
//				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			taris.close();
			gzipIn.close();
			bufIn.close();
			fileIn.close();
		}
	}
	
	public static void visitTARGZ(String targzFile) throws IOException {
		FileInputStream fileIn = null;
		BufferedInputStream bufIn = null;
		GZIPInputStream gzipIn = null;
		TarArchiveInputStream taris = null;
		try {
			fileIn = new FileInputStream(targzFile);
			bufIn = new BufferedInputStream(fileIn);
			gzipIn = new GZIPInputStream(bufIn); // first unzip the input file
			// stream.
			taris = new TarArchiveInputStream(gzipIn);
			System.out.println(taris.getCount());
			
			TarArchiveEntry entry = null;
			while ((entry = taris.getNextTarEntry()) != null) {
				if (entry.isDirectory())
					continue;
				// configure(taris, ((TarArchiveEntry) entry).getFile());
				// //process every entry in this tar file.
				System.out.println(entry);
				File file = entry.getFile();
				// System.out.println(file.getName());
				System.out.println(entry.getSize());
				// FileInputStream fo = new FileInputStream(file);

				byte[] b = new byte[(int) entry.getSize()];
				// fo.read(b);
				taris.read(b);
				taris.read(b, 0, (int) entry.getSize());
				System.out.println(b.length);
				System.out.println(b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			taris.close();
			gzipIn.close();
			bufIn.close();
			fileIn.close();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String file = "D:\\project\\cdh4\\cdh5.0.0\\parquet-format-1.0.0-cdh5.0.0.tar.gz";
		String a = GzipUtils.getCompressedFilename(file);
		System.out.println(a);

		try {
			parseTARGZ(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
