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

public class ProductModifyAction implements Action {

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
			product.setCategory_name(multi.getParameter("category_name"));
			product.setProduct_name(multi.getParameter("product_name"));
			product.setProduct_price(Integer.parseInt(multi.getParameter("product_price")));
			product.setProduct_details(multi.getParameter("product_details"));
			product.setProduct_stock(Integer.parseInt(multi.getParameter("product_stock")));
			product.setProduct_status(multi.getParameter("productStatus"));
			
			String check = multi.getParameter("check");
			System.out.println("check = " + check);
			if(check != null) { // 파일 변경 안한 경우
				product.setProduct_image(check);
			}else { // 파일 변경 한 경우
				String imgFileName = multi.getFilesystemName("imgUpload");
				product.setProduct_image(imgFileName);
			}
			
			result = pdao.productModify(product);
			
			// 상품 등록 실패할 경우 false를 반환합니다.
			if(result == false) {
				System.out.println("상품 수정 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "상품 수정 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("상품 수정 완료");
			forward.setRedirect(false);
			request.setAttribute("maintitle", "상품 수정");
			request.setAttribute("title", "상품 수정");
			request.setAttribute("body", "상품이 수정되었습니다.");
			request.setAttribute("path", "ProductDetail.ad?product_code=" + product.getProduct_code());
			forward.setPath("Modal/SuccessModal.jsp");
			return forward;
		}catch(IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "상품 업데이트 실패입니다.");
			forward.setRedirect(false);
			return forward;
		} // catch end
	}

}
