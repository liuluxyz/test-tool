package test_filecode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * liulu5
 * 2014-7-10
 */
public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("D:\\workspace\\workspace_tools\\test\\src\\test_filecode\\programinfo_20140710.dat");
//		File file = new File("C:\\Users\\liulu5\\Desktop\\get\\a.dat");
		
//		File file = new File(args[0]);
		
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
            	
//            	String newText = new String(new String(tempString.getBytes(), "GB2312").getBytes("UTF-8"));
            	
            	String[] codes = new String[]{
            			"iso-8859-1",
            			"iso8859-1",
            			"ISO8859-1",
            			"ISO-8859-1",
            			"ISO-8859-2",
            			"ISO-8859-3",
            			"ISO-8859-4",
            			"ISO-8859-5",
            			"ISO-8859-6",
            			"ISO-8859-7",
            			"GBK",
            			"gbk",
            			"GB2312",
            			"gb2312",
            			"UTF-8",
            			"latin1"
            	};
            	System.out.println(tempString);
            	System.out.println();
            	for(String code : codes){
            		String newText = new String(tempString.getBytes(code), "UTF-8");
                    System.out.println(code + " : " + newText);
            	}
                System.out.println();
                for(String code : codes){
            		String newText = new String(tempString.getBytes(), 0, tempString.length(), code);
                    System.out.println(code + " : " + new String(newText.getBytes("UTF-8")));
            	}
                break;
//                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}

}

