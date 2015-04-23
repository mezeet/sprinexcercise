package goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GoodsListService {
	
	private GoodsDao goodsDao;
	
	@Autowired
	@Qualifier("list")
	public void GoodsDao(GoodsDao goodsDao){
		this.goodsDao = goodsDao;
	}
	
	public void service(){
		System.out.println("GoodsListService.service()");
		goodsDao.list();
	}
}
