package git.wangwangyuwan.demo;

import git.wangwangyuwan.demo.es.Item;
import git.wangwangyuwan.demo.es.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private ItemRepository repository;
    @Test
    public void insert() {
        Item s = new Item(1L,"标题","类别","品牌",2000D,"");
        repository.save(s);
    }

    @Test
    public void contextLoads() {
        template.createIndex(Item.class);
//        template.deleteIndex(Item.class);
    }

}
