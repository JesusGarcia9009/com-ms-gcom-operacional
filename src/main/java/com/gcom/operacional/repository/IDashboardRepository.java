package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.Product;

/**
 * IDashboardRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IDashboardRepository extends CrudRepository<Product, Long> {


	@Query(value =  "SELECT"
			+ "	   (select count(1) as product_major from public.product p ), "
			+ "	   (select count(1) as product_minor from public.product p where p.stock <= 0), "
			+ "    (select count(1) as bill_of_buy_number "
			+ "       from public.bill_of_buy bob "
			+ "      where EXTRACT(MONTH FROM bob.generation_date) = EXTRACT(MONTH FROM current_date) AND "
			+ "            EXTRACT(year FROM bob.generation_date) = EXTRACT(year FROM current_date) );", nativeQuery = true)
    Object getDashboardWidgetData();
	
	@Query(value =  " select count(1) as quotation_number,  "
			+ "	 		     sum((qp.amount * qp.sale_price)+(((qp.amount * qp.sale_price) * q.iva)/100)-(((((qp.amount * qp.sale_price) * q.iva)/100)*q.discount)/100 )) as quotation_amount "
			+ "	        from public.quotation q  "
			+ "	             inner join public.quotation_product qp ON qp.id_quotation = q.id  "
			+ "	       where EXTRACT(MONTH FROM q.generation_date) = EXTRACT(MONTH FROM current_date) AND  "
			+ "	             EXTRACT(year FROM q.generation_date) = EXTRACT(year FROM current_date);", nativeQuery = true)
    Object getDashboardWidgetQuotationData();
	
	@Query(value =  " select count(1) as order_note_number,  "
			+ "	 		     sum((onp.amount * onp.sale_price)+(((onp.amount * onp.sale_price) * on2.iva)/100)-(((((onp.amount * onp.sale_price) * on2.iva)/100) * on2.discount)/100 )) as order_note_amount "
			+ "	        from public.order_note on2  "
			+ "	   		     inner join public.order_note_product onp ON onp.id_order_note = on2.id  "
			+ "	       where EXTRACT(MONTH FROM on2.generation_date) = EXTRACT(MONTH FROM current_date) AND  "
			+ "	             EXTRACT(year FROM on2.generation_date) = EXTRACT(year FROM current_date) ;", nativeQuery = true)
    Object getDashboardWidgetOrderNoteData();
	
	@Query(value =  "SELECT to_char(date_trunc('day', current_date - offs), 'TMDay') AS day_of_week, count(q.id) AS sales_count"
			+ "	       FROM generate_series(0, 6) AS offs"
			+ "	            LEFT JOIN public.quotation q ON q.generation_date >= date_trunc('day', current_date - offs)"
			+ "	                      AND q.generation_date <  date_trunc('day', current_date - offs) + interval '1 day'"
			+ "    GROUP BY offs"
			+ "    ORDER BY offs desc;", nativeQuery = true)
    List<Object> getDashboardQuotationDataCurrentWeek();
	
	@Query(value =  "SELECT to_char(date_trunc('day', current_date - offs), 'TMDay') AS day_of_week, count(on2.id) AS sales_count "
			+ "	  FROM generate_series(0, 6) AS offs "
			+ "	       LEFT JOIN public.order_note on2 ON on2.generation_date >= date_trunc('day', current_date - offs) "
			+ "	                 AND on2.generation_date <  date_trunc('day', current_date - offs) + interval '1 day' "
			+ "  GROUP BY offs "
			+ "  ORDER BY offs desc;", nativeQuery = true)
    List<Object> getDashboardOrderNoteDataCurrentWeek();
	
	@Query(value =  "  select p.id, p.description, (br.code || '-' || ugr.code || '-' || mr.code || '-' || sr.code) as gis ,  sum (onp.amount) as vendidos "
			+ "          from public.order_note odn  "
			+ "	     		  inner join public.order_note_product onp on onp.id_order_note = odn.id  "
			+ "	     		  inner join public.product p on p.id = onp.id_product "
			+ "	     		  inner join public.model mr on mr.id = p.id_model  "
			+ "	     		  inner join public.brand br on br.id = mr.id_brand  "
			+ "	     		  inner join public.universal_groups ugr on ugr.id = p.id_universal_groups  "
			+ "	     		  inner join public.sources sr on sr.id = p.id_sources   "
			+ "         where EXTRACT(MONTH FROM odn.generation_date) = EXTRACT(MONTH FROM current_date) AND "
			+ "               EXTRACT(year FROM odn.generation_date) = EXTRACT(year FROM current_date) "
			+ "      group by p.id, br.code,ugr.code, mr.code, sr.code "
			+ "      order by vendidos desc limit 5", nativeQuery = true)
    List<Object> getDashboardBestSellingProductsMonth();
	
	@Query(value =  "  select p.id, p.description, (br.code || '-' || ugr.code || '-' || mr.code || '-' || sr.code) as gis ,  sum (onp.amount) as vendidos "
			+ "          from public.order_note odn  "
			+ "	     		  inner join public.order_note_product onp on onp.id_order_note = odn.id  "
			+ "	     		  inner join public.product p on p.id = onp.id_product "
			+ "	     		  inner join public.model mr on mr.id = p.id_model  "
			+ "	     		  inner join public.brand br on br.id = mr.id_brand  "
			+ "	     		  inner join public.universal_groups ugr on ugr.id = p.id_universal_groups  "
			+ "	     		  inner join public.sources sr on sr.id = p.id_sources   "
			+ "         where EXTRACT(MONTH FROM odn.generation_date) = EXTRACT(MONTH FROM current_date) AND "
			+ "               EXTRACT(year FROM odn.generation_date) = EXTRACT(year FROM current_date) "
			+ "      group by p.id, br.code,ugr.code, mr.code, sr.code "
			+ "      order by vendidos desc limit 5", nativeQuery = true)
    List<Object> getDashboardLesserSellingProductsMonth();
    
	
}
