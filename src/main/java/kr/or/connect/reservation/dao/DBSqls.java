package kr.or.connect.reservation.dao;

public class DBSqls {
	public static final String SELECT_All_PRODUCTS = "SELECT display_info.id, product.category_id, product.description, product.content, display_info.place_name, file_info.save_file_name FROM product "
			+ "JOIN display_info ON  product.id = display_info.product_id "
			+ "JOIN product_image ON  product.id = product_image.product_id AND product_image.type ='th' "
			+ "JOIN file_info ON product_image.file_id = file_info.id "
			+ "order by product.id DESC limit :start, :limit";

	public static final String SELECT_CATEGORY_PRODUCTS = "SELECT display_info.id, product.category_id, product.description, product.content, display_info.place_name, file_info.save_file_name FROM product "
			+ "JOIN display_info ON  product.id = display_info.product_id "
			+ "JOIN product_image ON  product.id = product_image.product_id AND product_image.type ='th' "
			+ "JOIN file_info ON product_image.file_id = file_info.id " + "WHERE product.category_id = :category "
			+ "order by product.id DESC limit :start, :limit";

	public static final String SELECT_COUNT = "SELECT count(*) FROM product "
			+ "JOIN display_info ON  product.id = display_info.product_id "
			+ "JOIN product_image ON  product.id = product_image.product_id AND product_image.type ='th' "
			+ "JOIN file_info ON product_image.file_id = file_info.id ";

	public static final String SELECT_CATEGORY_COUNT = "SELECT count(*) FROM product "
			+ "WHERE category_id = :category";

	public static final String SELECT_CATEGORY = "SELECT id,name FROM category ";

	public static final String SELECT_PROMOTION = "SELECT promotion.id, promotion.product_id, file_info.save_file_name FROM promotion "
			+ "JOIN display_info ON  promotion.product_id = display_info.product_id "
			+ "JOIN product_image ON  promotion.product_id = product_image.product_id AND product_image.type ='th' "
			+ "JOIN file_info ON product_image.file_id = file_info.id";

	public static final String SELECT_DETAIL_PRODUCT = "SELECT file_info.save_file_name, product.description, product.content, display_info.id, display_info.place_lot, display_info.place_name, display_info.place_street, display_info.tel, display_info.opening_hours, display_info.product_id FROM display_info "
			+ "JOIN product ON  product.id = display_info.product_id "
			+ "JOIN product_image ON product_image.product_id = product.id AND product_image.type = 'ma' "
			+ "JOIN file_info ON file_info.id = product_image.file_id " + "WHERE display_info.id = :id";

	public static final String SELECT_DETAIL_PRODUCT_IMAGE = "SELECT file_info.save_file_name FROM product_image "
			+ "JOIN file_info ON product_image.file_id = file_info.id "
			+ "JOIN display_info ON display_info.product_id = product_image.product_id "
			+ "WHERE display_info.id = :id AND (product_image.type = 'et' or product_image.type = 'th') limit 2";

	public static final String SELECT_DETAIL_PRODUCT_MAP = "SELECT save_file_name FROM file_info "
			+ "JOIN display_info_image ON display_info_image.file_id = file_info.id " + "WHERE display_info_id = :id";
	
	public static final String SELECT_DETAIL_COMMENT_THREE = "SELECT DATE_FORMAT(reservation_info.reservation_date,'%Y-%m-%d') AS reservation_date,reservation_info.reservation_name,reservation_user_comment.score, reservation_user_comment.comment, file_info.save_file_name FROM reservation_info "
			+ "JOIN reservation_user_comment ON reservation_user_comment.product_id = reservation_info.product_id AND reservation_user_comment.reservation_info_id = reservation_info.id "
			+ "LEFT JOIN reservation_user_comment_image ON reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id "
			+ "LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id "
			+ "WHERE reservation_info.display_info_id = :id limit 3";
	
	public static final String SELECT_DETAIL_COMMENT = "SELECT DATE_FORMAT(reservation_info.reservation_date,'%Y-%m-%d') AS reservation_date,reservation_info.reservation_name,reservation_user_comment.score, reservation_user_comment.comment, file_info.save_file_name FROM reservation_info "
			+ "JOIN reservation_user_comment ON reservation_user_comment.product_id = reservation_info.product_id AND reservation_user_comment.reservation_info_id = reservation_info.id "
			+ "LEFT JOIN reservation_user_comment_image ON reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id "
			+ "LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id "
			+ "WHERE reservation_info.display_info_id = :id";
	
	public static final String SELECT_TICKET_INFO ="SELECT " + 
			"CASE " + 
			"		WHEN (product_price.price_type_name = 'A') THEN '성인'" + 
			"        WHEN (product_price.price_type_name = 'Y') THEN '청소년'" + 
			"        WHEN (product_price.price_type_name = 'B') THEN '유아'" + 
			"        WHEN (product_price.price_type_name = 'S') THEN '세트'" + 
			"        WHEN (product_price.price_type_name = 'D') THEN '장애인'" + 
			"        WHEN (product_price.price_type_name = 'C') THEN '지역주민'" + 
			"        WHEN (product_price.price_type_name = 'E') THEN '어얼리버드'" + 
			"        WHEN (product_price.price_type_name = 'V') THEN 'VIP석'" + 
			"        WHEN (product_price.price_type_name = 'R') THEN 'R석'" + 
			"	ELSE '기타'" + 
			"    END AS price_type," + 
			"product_price.price, product_price.discount_rate, product_price.id FROM product_price " + 
			"JOIN display_info ON display_info.product_id = product_price.product_id " + 
			"WHERE display_info.id = :id order by product_price.price";
	
	public static final String SELECT_TICKET_NUM = "SELECT ifnull(sum(reservation_info_price.count),0) FROM reservation_info_price " + 
			"JOIN product_price ON product_price.id = reservation_info_price.product_price_id " + 
			"JOIN display_info ON display_info.product_id = product_price.product_id " + 
			"WHERE display_info.id = :id";
	
	public static final String INSERT_RESERVE_INFO = "INSERT INTO reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, create_date, modify_date, cancel_flag) " + 
			"SELECT :productId, :id, :name, :tel, :email ,NOW(),NOW(),NOW(),0 FROM Dual " + 
			"WHERE NOT EXISTS (SELECT * FROM reservation_info WHERE display_info_id = :id and reservation_email = :email)";
	
	public static final String SELECT_RESERVE_ID ="SELECT id FROM reservation_info WHERE display_info_id = :id and reservation_email = :email";
	
	public static final String INSERT_RESERVE_TICKET = "INSERT INTO reservation_info_price (reservation_info_id, product_price_id, count) VALUES (:reservationInfoId, :productPriceId, :count)";
	
	public static final String SELECT_MYRESERVE_DOING = "SELECT reservation_info.id,reservation_info.display_info_id, product.description, display_info.place_name, DATE_FORMAT(display_info.create_date,'%Y-%m-%d') AS create_date FROM reservation_info " + 
			"JOIN display_info ON display_info.id = reservation_info.display_info_id " + 
			"JOIN product ON product.id = display_info.product_id " + 
			"WHERE reservation_info.cancel_flag = 0 and reservation_info.reservation_email = :email and TIMESTAMPDIFF(DAY,reservation_info.reservation_date, now()) < 1";
	
	public static final String SELECT_MYRESERVE_DONE = "SELECT reservation_info.id, reservation_info.display_info_id,product.description, display_info.place_name, DATE_FORMAT(display_info.create_date,'%Y-%m-%d') AS create_date FROM reservation_info " + 
			"JOIN display_info ON display_info.id = reservation_info.display_info_id " + 
			"JOIN product ON product.id = display_info.product_id " + 
			"WHERE reservation_info.cancel_flag = 0 and reservation_info.reservation_email = :email and TIMESTAMPDIFF(DAY,reservation_info.reservation_date, now()) >= 1";
	
	public static final String SELECT_MYRESERVE_CANCEL = "SELECT reservation_info.id, reservation_info.display_info_id, product.description, display_info.place_name, DATE_FORMAT(display_info.create_date,'%Y-%m-%d') AS create_date FROM reservation_info " + 
			"JOIN display_info ON display_info.id = reservation_info.display_info_id " + 
			"JOIN product ON product.id = display_info.product_id " + 
			"WHERE reservation_info.cancel_flag = 1 and reservation_info.reservation_email = :email";
	
	public static final String SELECT_MYRESERVE_TICKET = "SELECT reservation_info_price.count, product_price.price, " + 
			"	CASE  " + 
			"		WHEN (product_price.price_type_name = 'A') THEN '성인' " + 
			"        WHEN (product_price.price_type_name = 'Y') THEN '청소년' " + 
			"		WHEN (product_price.price_type_name = 'B') THEN '유아'" + 
			"		WHEN (product_price.price_type_name = 'S') THEN '세트' " + 
			"		WHEN (product_price.price_type_name = 'D') THEN '장애인' " + 
			"		WHEN (product_price.price_type_name = 'C') THEN '지역주민' " + 
			"		WHEN (product_price.price_type_name = 'E') THEN '어얼리버드' " + 
			"		WHEN (product_price.price_type_name = 'V') THEN 'VIP석' " + 
			"		WHEN (product_price.price_type_name = 'R') THEN 'R석' " + 
			"		ELSE '기타' " + 
			"	END AS price_type " + 
			"FROM reservation_info_price " + 
			"JOIN product_price ON product_price.id = reservation_info_price.product_price_id " + 
			"WHERE reservation_info_price.reservation_info_id = :id";
	
	public static final String UPDATE_MYRESERVE_CANCEL = "UPDATE reservation_info SET cancel_flag=1 WHERE id = :id";
	
	public static final String INSERT_MYRESERVE_COMMENT = "INSERT INTO reservation_user_comment(product_id, reservation_info_id, score, comment, create_date, modify_date) VALUES (:productId,:reserveInfoId, :score, :comment, NOW(), NOW())";

	public static final String INSERT_MYRESERVE_IMAGE = "INSERT INTO file_info(file_name, save_file_name, content_type, delete_flag, create_date, modify_date) VALUES (:fileName, :saveFileName, :contentType, 0, NOW(), NOW())"; 
	
	public static final String INSERT_COMMENT_IMAGE = "INSERT INTO reservation_user_comment_image(reservation_info_id, reservation_user_comment_id, file_id) VALUES (:reserveInfoId, :commentId, :fileId)";
}
