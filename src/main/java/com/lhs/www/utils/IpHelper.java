package com.lhs.www.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpHelper {

    /**
     * 检测用户ip是否在指定的ip段中
     * @param ip 用户ip
     * @param begip 起始ip
     * @param endip 结束ip
     * @return
     */
    public static boolean isInIp(String ip, String begip, String endip) {
        int[] inip, begipint, endipint;
        inip = getIp(ip);
        begipint = getIp(begip);
        endipint = getIp(endip);
        boolean isIn = false;
        for (int i = 0; i < 4; i++) {
            if (inip[i] >= begipint[i] && inip[i] <= endipint[i]) {
                isIn = true;
            } else {
                isIn = false;
                break;
            }
        }
        return isIn;
    }

    /**
     * 把ip转成int数组
     * @param ip
     * @return
     */
    private static int[] getIp(String ip) {
        String [] values = ip.split("\\.");
        int[] ips = new int [4];

        for (int i=0; i<4; i++) {
            ips[i] = Integer.parseInt(values[i]);
        }

        return ips;
    }






    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    public static long ipToLong(String strIp) {
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3+1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    //将十进制整数形式转换成127.0.0.1形式的ip地址
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        //直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        //将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        //将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        //将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    //获取本机MAC地址
    public static String getLocalMacAddress()
    {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String host =addr.getHostAddress();//获得本机IP
        String mac = "";
        StringBuffer sb = new StringBuffer();

        try
        {
            //获取mac地址
            NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByName(host));

            byte[] macs = ni.getHardwareAddress();
            //将mac地址转换为可见字符串格式
            for(int i=0; i<macs.length; i++)
            {
                mac = Integer.toHexString(macs[i] & 0xFF);

                if (mac.length() == 1)
                {
                    mac = '0' + mac;
                }

                sb.append(mac + "-");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        mac = sb.toString();
        mac = mac.substring(0, mac.length()-1);

        return mac;
    }

    /**
     *
     * @MethodName: macToLong
     * @Description: 将mac地址转换为长整型
     * @param strMac
     * @return
     * @return long
     * @throws
     */
    public static long macToLong(String strMac){

        String str = strMac.replace("-", "");
        long result = Long.valueOf(str, 16);
    	/*for(int i = 0 ; i < 5 ; i++){
    		int position = strMac.indexOf("-", pos);
    		String str = strMac.substring(pos,position);
    		mac[i] = Integer.parseInt(str, 16);
    		pos = position + 1;
    	}
    	String str = strMac.substring(pos);
    	mac[5] = Integer.parseInt(str, 16);
    	long result = mac[0];
    	for(int j = 1 ; j < 6 ; j++){
    		result = (result<<8) + mac[j];
    		//System.out.println(result);
    	}*/
        return result;
    }

    /**
     *
     * @MethodName: macToString
     * @Description: 将长整型转换为mac地址
     * @param longMac
     * @return
     * @return String
     * @throws
     */
    public static String macToString(long longMac){
        StringBuilder sb = new StringBuilder();
        String str = Long.toHexString(longMac);
        //若字符串长度为11位，则首位加0
        if(str.length() == 11){
            sb.append("0"+str.substring(0,1)+"-");
            for(int i=1;i<11;i+=2){
                sb.append(str.substring(i,i+2)+"-");
            }
        }
        //否则按正常处理
        else{
            for(int i=0;i<11;i+=2){
                sb.append(str.substring(i,i+2)+"-");
            }
        }
        return sb.substring(0,sb.length()-1).toString();
    }

    /**
     * 验证ip是否合法
     *
     * @param text
     *            ip地址
     * @return 验证信息
     */
    public static  boolean checkIp(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;//text + "\n是一个合法的IP地址！";
            } else {
                // 返回判断信息
                return false;//text + "\n不是一个合法的IP地址！";
            }
        }
        // 返回判断信息
        return false;
    }



    /**
     * 验证输入的服务器域名是否合法
     *
     * @param text
     *            域名
     * @return boolean
     */
    public static  boolean checkDomain(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex ="^([A-Z0-9a-z-]+\\.)+[A-Za-z]{2,5}$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 比较开始ip和结束ip的合法性
     * @param startIp
     * @param endIp
     * @return
     */
    public static boolean compare(String startIp,String endIp){
        if(null == startIp || "".equals(startIp) || null == endIp || "".equals(endIp)){
            return false;
        }
        boolean flag = false;
        String startips[]=startIp.split("\\.");
        String endIps[]=endIp.split("\\.");
        for(int i=0;i<startips.length;i++){
            if(Integer.parseInt(endIps[i])>Integer.parseInt(startips[i])){
                flag=true;
                break;
            }else{
                if(Integer.parseInt(endIps[i])==Integer.parseInt(startips[i])){
                    continue;
                }else{
                    break;
                }
            }
        }
        return flag;
    }


    public static void main (String a[]) {
		/*System.out.println(IpHelper.isIpAllowed("", ""));
		String ip = "10.232.232.255";
		System.out.println("the ip ="+ip+",check result is "+checkIp(ip));

		String domain = "fsdafafddsfsasfasssdf3.223.f.com.dn";
		System.out.println("the domain ="+domain+",check result is "+checkDomain(domain));*/


        //System.out.println(IpHelper.isInIp("192.168.40.44", "192.168.40.0", "192.168.40.255"));
    }

}
