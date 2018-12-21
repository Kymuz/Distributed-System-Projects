package ImgApp;


/**
* ImgApp/ImgServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from StoreImg.idl
* Wednesday, April 25, 2018 1:37:38 PM EET
*/

abstract public class ImgServerHelper
{
  private static String  _id = "IDL:ImgApp/ImgServer:1.0";

  public static void insert (org.omg.CORBA.Any a, ImgApp.ImgServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ImgApp.ImgServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ImgApp.ImgServerHelper.id (), "ImgServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ImgApp.ImgServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ImgServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ImgApp.ImgServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ImgApp.ImgServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ImgApp.ImgServer)
      return (ImgApp.ImgServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ImgApp._ImgServerStub stub = new ImgApp._ImgServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ImgApp.ImgServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ImgApp.ImgServer)
      return (ImgApp.ImgServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ImgApp._ImgServerStub stub = new ImgApp._ImgServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}