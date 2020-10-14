package quickstart.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-10-13T17:58:00.093-0300")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Integer> itemId;
	public static volatile SingularAttribute<Item, String> itemName;
	public static volatile SingularAttribute<Item, String> itemDescription;
	public static volatile SingularAttribute<Item, String> itemCategory;
}
