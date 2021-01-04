package rt.java.lang.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
/*
配合 {@link Target java.lang.annotation.Target} 使用
  Class, interface (including annotation type), or enum declaration
 1.   TYPE,
  Field declaration (includes enum constants)
 2.   FIELD,
 Method declaration
 3.   METHOD,
 Formal parameter declaration
 4.  PARAMETER,
 Constructor declaration
 5.  CONSTRUCTOR,
 Local variable declaration
 6.  LOCAL_VARIABLE,
 Annotation type declaration
 7.  ANNOTATION_TYPE,
 Package declaration
 8.  PACKAGE,
 Type parameter declaration
   @since 1.8
 9. TYPE_PARAMETER,
   Use of a type
  @since 1.8
 10. TYPE_USE */
@Target(ElementType.TYPE)
@interface  ElementTypeType{
}
@Target(ElementType.FIELD)
@interface  ElementTypeField{
}
@Target(ElementType.METHOD)
@interface  ElementTypeMethod{
}
@Target(ElementType.PARAMETER)
@interface  ElementTypeParameter{
}
@Target(ElementType.CONSTRUCTOR)
@interface  ElementTypeConstructor{
}
@Target(ElementType.LOCAL_VARIABLE)
@interface  ElementTypeLocalvariable{
}
@Target(ElementType.ANNOTATION_TYPE)
@interface  ElementTypeAnnotationtype{
}
@Target(ElementType.PACKAGE)
@interface  ElementTypePackage{
}
@Target(ElementType.TYPE_PARAMETER)
@interface  ElementTypeTypeparameter{
}
@Target(ElementType.TYPE_USE) //任意地方使用
@interface  ElementTypeTypeuse{
}

@ElementTypeType
public class ElementTypeDemo {
    @ElementTypeField
    private String foo;

    @ElementTypeConstructor
    public ElementTypeDemo(){
    }

    @ElementTypeMethod
    public void foo(@ElementTypeParameter String foo){
        @ElementTypeLocalvariable
        String foo1="";
    }

    public <@ElementTypeTypeparameter() T> void foo(){
        @ElementTypeTypeuse  //任意地方使用
        String foo1="";
    }
}
@ElementTypeAnnotationtype
@interface ElementTypeAnnotation{

}
