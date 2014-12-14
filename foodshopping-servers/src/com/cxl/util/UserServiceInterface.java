package com.cxl.util;

import java.util.ArrayList;

import javabean.ShoppingCateComment;
import javabean.ShoppingCateDetail;

public interface UserServiceInterface {
	ShoppingCateDetail querryShoppingCateDetail(int cate_id);

	ArrayList<ShoppingCateComment> querryShoppingCateComment(int cate_id) throws Exception;
}
