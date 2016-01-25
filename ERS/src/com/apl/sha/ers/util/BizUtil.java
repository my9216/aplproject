package com.apl.sha.ers.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apl.sha.ers.model.BookingCtn;
import com.apl.sha.ers.model.DepotCtn;

public class BizUtil {
	public static List alctDepotCtns(List ctns,int ctnQty) {
		List list=new ArrayList();
		int allRemainQty=0;
		int alctQty=0;
		for(Iterator it=ctns.iterator();it.hasNext();) {
			DepotCtn ctn=(DepotCtn) it.next();
			int dptRemainQty=ctn.getInitalQty()-ctn.getReservedQty()-ctn.getPickupedQty();
				dptRemainQty=(dptRemainQty>0)?dptRemainQty:0;
			allRemainQty=allRemainQty+dptRemainQty;
		}
		
		int multi8 = 0;
		int remain8=0;
		int ctnQuantity=0;
		multi8 = ctnQty/8;
		remain8 = ctnQty%8;
		if(allRemainQty>0) {
			int dptRemainQty=0;
			for(Iterator it=ctns.iterator();it.hasNext();) {
				BookingCtn bkgCtn=new BookingCtn();
				DepotCtn ctn=(DepotCtn) it.next();
				dptRemainQty=ctn.getInitalQty()-ctn.getReservedQty()-ctn.getPickupedQty();
				if(multi8>0)
				{
					alctQty=(int) Math.ceil((multi8 * (double)(dptRemainQty)/allRemainQty));
				}else {
					alctQty=0;
					ctnQuantity = ctnQty;
				}
//				if(ctn.getCtnCode().contains("20")) {
//					if(alctQty<ctnQty&&alctQty%2==1) alctQty++;
//				}
				if(alctQty>multi8)alctQty=multi8;
				if (multi8>0) {
					ctnQuantity = alctQty*8;
				}else
				{
					ctnQuantity = remain8;
				}
				bkgCtn.setDepotCode(ctn.getDepotCode());
				bkgCtn.setCtnQty(ctnQuantity);
				list.add(bkgCtn);
				multi8 = multi8-alctQty;
				ctnQty=ctnQty-ctnQuantity;
				if(ctnQty==0) break;
				allRemainQty=allRemainQty-dptRemainQty;
			}
		}else {
			int dptCount=ctns.size();
			for(Iterator it=ctns.iterator();it.hasNext();) {
				BookingCtn bkgCtn=new BookingCtn();
				DepotCtn ctn=(DepotCtn) it.next();
				if(multi8>0)
				{
					alctQty=(int) Math.ceil(multi8 * (double)1/dptCount);
				}else {
					ctnQuantity = ctnQty;
				}
//				if(ctn.getCtnCode().contains("20")) {
//					if(alctQty<ctnQty&&alctQty%2==1) alctQty++;
//				}
				if(alctQty>multi8)alctQty=multi8;
				if (multi8>0) {
					ctnQuantity = alctQty*8;
				}else
				{
					ctnQuantity = remain8;
				}
				bkgCtn.setDepotCode(ctn.getDepotCode());
				bkgCtn.setCtnQty(ctnQuantity);
				list.add(bkgCtn);
				multi8 = multi8-alctQty;
				ctnQty=ctnQty-ctnQuantity;
				if(ctnQty==0) break;
				dptCount--;
			}
		}
		return list;
	}
}
