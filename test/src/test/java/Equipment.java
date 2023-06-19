import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY,
    property = "name")
@JsonSubTypes(value = {
     @JsonSubTypes.Type(value = Daedalus.class, name = "Daedalus"),
     @JsonSubTypes.Type(value = HugeDrink.class, name = "Huge drink"),
     @JsonSubTypes.Type(value = StarWand.class, name = "Star wand"),
})
public interface Equipment {
 
}