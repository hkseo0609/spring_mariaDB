package com.gms.web.proxy;


public class BlockHandler{
	
	public static int[] attr(PaginationProxy pxy){
		int[] result = new int[6];
		int theNumberOfPage = 0, startPage=0, endPage = 0;
		
		theNumberOfPage = (pxy.getTheNumberOfRows() % pxy.getPageSize())==0 ? pxy.getTheNumberOfRows() / pxy.getPageSize() : pxy.getTheNumberOfRows() / pxy.getPageSize() + 1;
		startPage = pxy.getPageNumber() -((pxy.getPageNumber()-1)%pxy.getBlockSize());
		endPage = (startPage + pxy.getBlockSize() - 1 <= theNumberOfPage) ?  startPage+pxy.getBlockSize()-1 : theNumberOfPage;
		
		result[0]=pxy.getPageNumber();
		result[1]=theNumberOfPage;
		result[2]=startPage;
		result[3]=endPage;
		result[4]=startPage-(theNumberOfPage/pxy.getBlockSize()); //prevBlock
		result[5]=theNumberOfPage-(startPage+(theNumberOfPage/pxy.getBlockSize())); //nextBlock
		
		System.out.println(
				"pageNumber is "+result[0]+",\n"+
				"theNumberOfPage is "+result[1]+",\n"+
				"startPage is "+result[2]+",\n"+
				"endPage is "+result[3]+",\n"+
				"prevBlock is "+result[4]+",\n"+
				"nextBlock is "+result[5]
				);
		
		
		return result;
	}
}
