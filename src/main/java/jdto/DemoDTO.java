package jdto;

public class DemoDTO extends BaseDTO {
    private String _CODE = "code";

    public DemoDTO() {
        super();
    }

    public DemoDTO(String message) {
        this.jsonObject = new JsonObject(message);
    }

    public String getCode() {
        return this.jsonObject.getString(_CODE);
    }

    public void setCode(String code) {
        this.jsonObject.put(_CODE, code);
    }

}