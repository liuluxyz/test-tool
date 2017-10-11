package test_jexl;

//import com.ailk.cloudetl.script.ScriptEnv;
//import com.ailk.cloudetl.script.ScriptParser;
//import com.ailk.cloudetl.script.ScriptParserFactory;
//import com.ailk.cloudetl.script.ScriptParserType;

public class TestJEXL {

	public static void testIf() {
//		String str = "if (t_123 > 100){ true; }else if (t_123 <= 100) false";
//		String str = "if (t_123 == '0' && t_234==0){ true; }else false";
//		String str = "${\'/wap_log/gprsOnOffLine/\' + date + \'/ftp/\'+chkFile.substring(chkFile.lastIndexOf(\"/\")+1)}";
//		String scri = "FILE_NAME_.substring(2,6)";
		String str = "if(a==1 && b==1 && c==1 && d==1 && e==1){true;} else false";
			
//		ScriptParser parser = ScriptParserFactory.creator(ScriptParserType.Jexl);
//		ScriptEnv env = new ScriptEnv();
//		env.setVar("date", "201201");
//		env.setVar("chkFile", "/a/b/c/d/check.chk");
//		env.setVar("FILE_NAME_", "abcdefghijk");
//		env.setVar("a", "1");
//		env.setVar("b", "1");
//		env.setVar("c", "1");
//		env.setVar("d", "1");
//		env.setVar("e", "1");
//		parser.setEnv(env);
//		parser.setScript(str);
//		System.out.println(parser.getValue());
	}

	
	/**
	 * @param args
	 * 下午10:35:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testIf();
	}

}
