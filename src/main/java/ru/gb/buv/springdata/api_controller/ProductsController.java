
package ru.gb.buv.springdata.api_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.buv.springdata.entity.Product;
import ru.gb.buv.springdata.service.ProductService;
import java.util.List;
/**
 * Делал всё на GET запросах, потому,
 * что не использовал HTML...
 * Навигация/управление по адресной строке (запрос-ответJson)
 * */
/*    2. Сделать RestController позволяющий выполнять следующий набор операции над этой сущностью:
            получение товара по id [ GET .../app/products/{id} ]
            получение всех товаров [ GET .../app/products ]
            создание нового товара [ POST .../app/products ]
            удаление товара по id.[ GET .../app/products/delete/{id} ]
            (Замечание: пока делаем немного не по правилам REST API, эта тема будет разбираться на следующих занятиях,
            поэтому удаление выполняется через http-метод GET, а не DELETE)*/

@RestController
@RequestMapping("/app")//-отправная точка!!!
public class ProductsController {
    @Autowired
    ProductService productService;
//*********************************
    //Получение списка продукта
    @GetMapping
    public List<Product> getProductList(){
        return productService.getProductList();
    }
    //Получение сущности по id
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
        /*throw new UnsupportedOperationException();*/
    }
    //Добавление
    @GetMapping ("/add/{title}/{cost}")
    public List<Product> addNewProduct(@PathVariable String title
            ,@PathVariable Long cost){
        productService.saveProduct(productService.createProduct(title,cost));
        return productService.getProductList();
    }
    //Удаление
    @GetMapping("/delete/{id}")
    public List<Product> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return productService.getProductList();
    }
    /*3. * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
            (в трех вариантах: товары дороже min цены, товары дешевле max цены, или товары,
             цена которых находится в пределах min-max).*/
    //Фильтрация
    @GetMapping("filter/cost/asc")
    public List<Product> filterCostAsc(){
        return productService.filterCostAsc();
    }
    @GetMapping("filter/cost/desc")
    public List<Product> filterCostDesc(){
        return productService.filterCostDesc();
    }
    @GetMapping("filter/cost/range/{min}/{max}")
    public List<Product> filterCostRange(@PathVariable Long min, @PathVariable Long max){
        return productService.filterCostRange(min,max);
    }

}
