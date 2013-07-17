package cn.luck.test;

import java.text.SimpleDateFormat;
import java.util.List;
import cn.luck.bean.DoubleBallBean;
import cn.luck.dao.DoubleBallDAO;

public class Test {

	public static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test=new Test();
		try {
			//test.insertData("2013-01-01","2013-07-31");
			test.sumData("2013-06-21 12:12:12",10);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//--------------插入数据
	public void insertData(String ds,String de) throws Exception{
		String sourceUrlString="http://baidu.lecai.com/lottery/draw/list/50?ds="+ds+"&de="+de;
		DisplayAllElements internetData=new DisplayAllElements();
		List<DoubleBallBean> listinternetData=internetData.getDate(sourceUrlString);
		
		DoubleBallDAO dao=new DoubleBallDAO();
		for(DoubleBallBean ballBean:listinternetData){
			if(dao.selectDoubleBallID(ballBean.getId())==0){
				dao.insertDoubleBall(ballBean);
			}
		}
	}
	
	//统计数据
	public void sumData(String ds,int number)throws Exception{
		DoubleBallDAO dao=new DoubleBallDAO();
		List<DoubleBallBean> listinternetData=dao.findDoubleBalls(Test.dateFormat.parse(ds), number);
		
		int[] sum =new int[34];
		System.out.println("-----------列表-------------------总数:"+listinternetData.size());
		for(DoubleBallBean bena:listinternetData){
			sum[bena.getRed1()]=sum[bena.getRed1()]+1;
			sum[bena.getRed2()]=sum[bena.getRed2()]+1;
			sum[bena.getRed3()]=sum[bena.getRed3()]+1;
			sum[bena.getRed4()]=sum[bena.getRed4()]+1;
			sum[bena.getRed5()]=sum[bena.getRed5()]+1;
			sum[bena.getRed6()]=sum[bena.getRed6()]+1;
			this.printBall(bena);
		}
		String[] sumRed2=new String[33];
		System.out.println("-----------红球-------------------");
		for(int i=1;i<=33;i++){
			//System.out.println(i+"号:"+sum[i]);
			
			if(sumRed2[sum[i]]==null){
				sumRed2[sum[i]]=i+"";
			}else{
				sumRed2[sum[i]]=sumRed2[sum[i]]+","+i;
			}
		}
		
		int[] sumBlue =new int[17];
		String[] sumBlue2=new String[16];
		for(DoubleBallBean bena:listinternetData){
			sumBlue[bena.getBlue()]=sum[bena.getBlue()]+1;
		}
		System.out.println("-----------篮球-------------------");
		for(int i=1;i<=16;i++){
			//System.out.println(i+"号:"+sumBlue[i]);
			if(sumBlue2[sumBlue[i]]==null){
				sumBlue2[sumBlue[i]]=i+"";
			}else{
				sumBlue2[sumBlue[i]]=sumBlue2[sumBlue[i]]+","+i;
			}
		}
		System.out.println("-----------统计二红球-------------------");
		for(int i=0;i<sumRed2.length;i++){
			if(sumRed2[i]!=null){
				System.out.println("出现次数"+i+":>>>"+sumRed2[i]);
			}
		}
		System.out.println("-----------统计二篮球-------------------");
		for(int i=0;i<sumBlue2.length;i++){
			if(sumBlue2[i]!=null){
				System.out.println("出现次数"+i+":>>>"+sumBlue2[i]);
			}
		}
	}
	
	private void printBall(DoubleBallBean ball){
		System.out.println(Test.dateFormat.format(ball.getPublishTime())+" 期数:"+ball.getId()+"  红球:"+ball.getRed1()+","+ball.getRed2()+","+ball.getRed3()+","+ball.getRed4()+","+ball.getRed5()+","+ball.getRed6()+"   篮球:"+ball.getBlue());
	}
}
