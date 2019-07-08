package cn.maaa.test;

/**
 * 32位二进制与点分十进制的ip互转
 * @author mazh
 * @date 2019年07月08日 11:42 
 */
public class IpConvert {

	public static String ipTo32(String ip) {
		StringBuilder sb = new StringBuilder();
		String[] sections = ip.split("\\.");
		for (String str : sections) {
			long number = Long.parseLong(str);
			StringBuilder sub = new StringBuilder();
			for(int j = 0;j< 8;j++){
				if(number % 2 == 0)
					sub.append(0);
				else
					sub.append(1);
				number = number >> 1;
			}
			sb.append(sub.reverse());
		}
		return sb.toString();
	}


	public static void main(String[] args) {

		String ip = "192.168.0.1";
		System.out.println(ipTo32(ip));

	}

}
