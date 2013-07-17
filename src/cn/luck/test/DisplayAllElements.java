package cn.luck.test;

import net.htmlparser.jericho.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;

import cn.luck.bean.DoubleBallBean;

public class DisplayAllElements {
	public List<DoubleBallBean> getDate(String sourceUrlString) throws Exception {
		Source source=new Source(new URL(sourceUrlString));
		List<Element> elementList=source.getAllElements(HTMLElementName.TR);
		System.out.println(elementList.size());
		List<DoubleBallBean> dballList=new ArrayList<DoubleBallBean>();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		DoubleBallBean dball=null;
		for (Element element : elementList) {
			if("bgcolor1".equals(element.getAttributeValue("class"))){
				//System.out.println(element.toString());
				dball=new DoubleBallBean();
				List<Element> elementTD1=element.getAllElements(HTMLElementName.TD);
				for(Element tdem : elementTD1){
					if("td1".equals(tdem.getAttributeValue("class"))){
						System.out.println("");
						System.out.print("时间:"+tdem.getContent().toString());
						dball.setPublishTime(dateFormat.parse(tdem.getContent().toString().trim()));
					}
					if("td2".equals(tdem.getAttributeValue("class"))){
						System.out.print("期数:"+tdem.getFirstElement(HTMLElementName.A).getContent().toString()+"   ");
						dball.setId(Integer.parseInt(tdem.getFirstElement(HTMLElementName.A).getContent().toString().trim()));
					}
					if("td3".equals(tdem.getAttributeValue("class"))){
						List<Element> elementSPAN=tdem.getAllElements(HTMLElementName.SPAN);
						String blueballStr="";
						for(Element spamem : elementSPAN){
							if("ball_1".equals(spamem.getAttributeValue("class"))){
								System.out.print(spamem.getContent().toString()+",");
								blueballStr=blueballStr+spamem.getContent().toString().trim()+",";
							}
							if("ball_2".equals(spamem.getAttributeValue("class"))){
								System.out.print("篮球:("+spamem.getContent().toString()+")");
								dball.setBlue(Integer.parseInt(spamem.getContent().toString().trim()));
							}
						}
						String[] blueball=blueballStr.split(",");
						if(blueball.length==6){
							dball.setRed1(Integer.parseInt(blueball[0]));
							dball.setRed2(Integer.parseInt(blueball[1]));
							dball.setRed3(Integer.parseInt(blueball[2]));
							dball.setRed4(Integer.parseInt(blueball[3]));
							dball.setRed5(Integer.parseInt(blueball[4]));
							dball.setRed6(Integer.parseInt(blueball[5]));
						}else{
							System.out.println("红球数量不正确");
						}
					}
				}
				dballList.add(dball);
			}
			if("bgcolor2".equals(element.getAttributeValue("class"))){
				//System.out.println(element.toString());
				dball=new DoubleBallBean();
				List<Element> elementTD1=element.getAllElements(HTMLElementName.TD);
				for(Element tdem : elementTD1){
					if("td1".equals(tdem.getAttributeValue("class"))){
						System.out.println("");
						System.out.print("时间:"+tdem.getContent().toString());
						dball.setPublishTime(dateFormat.parse(tdem.getContent().toString()));
					}
					if("td2".equals(tdem.getAttributeValue("class"))){
						System.out.print("期数:"+tdem.getFirstElement(HTMLElementName.A).getContent().toString()+"   ");
						dball.setId(Integer.parseInt(tdem.getFirstElement(HTMLElementName.A).getContent().toString()));
					}
					if("td3".equals(tdem.getAttributeValue("class"))){
						List<Element> elementSPAN=tdem.getAllElements(HTMLElementName.SPAN);
						String blueballStr="";
						for(Element spamem : elementSPAN){
							if("ball_1".equals(spamem.getAttributeValue("class"))){
								System.out.print(spamem.getContent().toString()+",");
								blueballStr=blueballStr+spamem.getContent().toString()+",";
							}
							if("ball_2".equals(spamem.getAttributeValue("class"))){
								System.out.print("篮球:("+spamem.getContent().toString()+")");
								dball.setBlue(Integer.parseInt(spamem.getContent().toString()));
							}
						}
						String[] blueball=blueballStr.split(",");
						if(blueball.length==6){
							dball.setRed1(Integer.parseInt(blueball[0]));
							dball.setRed2(Integer.parseInt(blueball[1]));
							dball.setRed3(Integer.parseInt(blueball[2]));
							dball.setRed4(Integer.parseInt(blueball[3]));
							dball.setRed5(Integer.parseInt(blueball[4]));
							dball.setRed6(Integer.parseInt(blueball[5]));
						}else{
							System.out.println("红球数量不正确");
						}
					}
				}
				dballList.add(dball);
			}
		}
		return dballList;
  }
}
