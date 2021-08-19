package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class ProductAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		ProductDAO pdao = new ProductDAO();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "LaptopImgUpload";
		
		int filesize = 5 * 1024 * 1024;
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, filesize,
							"UTF-8", new DefaultFileRenamePolicy());
			
			product.setProduct_code(multi.getParameter("product_code"));
			product.setCategory_code(multi.getParameter("category_code"));
			product.setProduct_name(multi.getParameter("product_name"));
			product.setProduct_price(Integer.parseInt(multi.getParameter("product_price")));
			product.setProduct_details(multi.getParameter("product_details"));
			product.setProduct_status(multi.getParameter("productStatus"));
			String imgFileName = multi.getFilesystemName("imgUpload");
			product.setProduct_image(imgFileName);
			
			result = pdao.productInsert(product);
			
			if(result == false) {
				System.out.println("상품 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "상품 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			
			System.out.println("상품 등록 완료");
			forward.setRedirect(false);
			request.setAttribute("maintitle", "상품 등록");
			request.setAttribute("title", "상품 등록");
			request.setAttribute("body", "상품이 등록되었습니다.");
			request.setAttribute("path", "ProductList.ad");
			forward.setPath("Modal/SuccessModal.jsp");
			return forward;
		}catch(IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "상품 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		} // catch end
	}

}
