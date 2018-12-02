package version.changes.java9;

import version.changes.MyResource;

public class TryWithResources {

     /* The JDK9 example shows that the resource can be
        instantiated outside the try block. However, it must
        be final OR effectively final for this to compile.
        This is because the compiler MUST be sure about
        which close method to call.
     */
    public static void jdk9(int resID) {
        MyResource res = new MyResource(resID);
        try (res) {
            res.process();
            // If we create a new res object
            // then it is no longer effectively final
            // and will result in a compiler error.
            // If you remove the comment from the line
            // below you will see.

            // res = new MyResource(0);
        } catch (Exception ex) {
            System.out.println("Handling " + ex.toString());

        }
    }

    /* This is another JDK9 example showing
       how you can use several resources in the try block.
       In this case 2 are instantiated outside the block and
       one inside the block.
    */
    public static void jdk9Multiple(int resID) {
        MyResource res = new MyResource(resID);
        MyResource res2 = new MyResource(resID);
        try (res; res2; final MyResource res3 = new MyResource(resID)) {
            res.process();
            res2.process();
            res3.process();
        } catch (Exception ex) {
            System.out.println("Handling " + ex.toString());
        }
    }

}
