package poly.edu.lab6java5.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.lab6java5.dao.CategoryDAO;
import poly.edu.lab6java5.entity.Category;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO dao; // Làm việc với bảng Categories

    // Hiển thị danh sách
    @RequestMapping("/category/index")
    public String index(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);

        List<Category> items = dao.findAll();
        model.addAttribute("items", items);

        return "index"; // Không cần thư mục con vì file nằm trực tiếp trong templates
    }

    // Chỉnh sửa (Edit) có id
    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = dao.findById(id).orElse(new Category());
        model.addAttribute("item", item);

        List<Category> items = dao.findAll();
        model.addAttribute("items", items);

        return "index";
    }

    // Nếu người dùng vào /category/edit mà không có id → tránh lỗi 404
    @RequestMapping({"/category/edit", "/category/edit/"})
    public String editWithoutId() {
        return "redirect:/category/index";
    }

    // Tạo mới (Create)
    @PostMapping("/category/create")
    public String create(Category item) {
        dao.save(item);
        return "redirect:/category/index";
    }

    // Cập nhật (Update)
    @PostMapping("/category/update")
    public String update(Category item) {
        dao.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    // Xóa (Delete foreign key constraint)
    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            dao.deleteById(id);
            model.addAttribute("message", "Xóa thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Không thể xóa vì còn dữ liệu liên quan!");
        }
        model.addAttribute("item", new Category());
        model.addAttribute("items", dao.findAll());
        return "index";
    }
    @RequestMapping("/category/clean")
    public String clean() {
        List<Category> list = dao.findAll();
        for (Category c : list) {
            if (c.getId() == null || c.getId().trim().isEmpty()
                    || c.getName() == null || c.getName().trim().isEmpty()) {
                dao.delete(c);
            }
        }
        return "redirect:/category/index";
    }
}
