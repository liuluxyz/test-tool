package test_InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ip = "10.1.22.94";
		String[] addrs = ip.split("\\.");
		byte[] addrB = new byte[addrs.length];
		for(int i=0; i<addrs.length; i++){
			addrB[i] = Byte.parseByte(addrs[i]);
		}
		
		try {
			InetAddress ia = InetAddress.getByAddress(addrB);
			System.out.println(ia.getHostAddress());
			System.out.println(ia.getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
