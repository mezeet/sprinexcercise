package goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GoodsViewService {
	
	private GoodsDao goodsDao;
	
	@Autowired
	@Qualifier("view")
	public void setGoodsDao(GoodsDao goodsDao){
		this.goodsDao = goodsDao;
	}
	
	public void service(){
		System.out.println("GoodsListService.service()");
		goodsDao.list();
	}
}
