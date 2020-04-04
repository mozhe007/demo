package framework.spring.bean.name1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SameName {
    public String name = "1";

    @Autowired
    private framework.spring.bean.name2.SameName sameName;


}
