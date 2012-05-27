/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openide.nodes.PropertySupport;
import org.openide.util.Exceptions;
import org.openscada.opc.lib.da.*;
import org.openscada.opc.lib.da.browser.Leaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author justin
 */
public class OPCLeafProperty<T> extends PropertySupport<T> {

    private Item item;
    private Leaf leaf;
    private boolean forceHardwareRead;
    Logger logger = LoggerFactory.getLogger(OPCLeafProperty.class);
    int comDataType = -1;
    private static final ExecutorService asyncLoggingThread = Executors.newSingleThreadExecutor(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "OPCLeafPropertyLogger");
        }
    });

    private OPCLeafProperty(String name, Class<T> type, String displayName, String shortDescription, Item item, Leaf leaf, boolean forceHWRead) {
        super(name, type, displayName, shortDescription, true, true);
        this.item = item;
        this.leaf = leaf;
        this.forceHardwareRead = forceHWRead;

    }

    public static OPCLeafProperty opcLeafPropertyFactory(Leaf l, Server s, Group serverGroup) throws AddFailedException, JIException {
        return opcLeafPropertyFactory(l, s, serverGroup, false);
    }

    public static OPCLeafProperty opcLeafPropertyFactory(Leaf l, Server s, Group serverGroup, boolean forceHWRead) throws AddFailedException, JIException {
        String name = l.getName();
        String displayName = l.getName();
        String shortDescription = l.getItemId();

        Item i = serverGroup.addItem(l.getItemId());

        //Must do an initial read of the item, to see what type it is...
        ItemState itemState = i.read(forceHWRead);
        JIVariant jIVariant = itemState.getValue();
        int type = jIVariant.getType();
        Class c;
        switch (type) {

            case JIVariant.VT_I1:
            case JIVariant.VT_UI1:

                c = Byte.class;
                break;
            case JIVariant.VT_I2:
            case JIVariant.VT_UI2:
                c = Short.class;
                break;
            case JIVariant.VT_I4:
            case JIVariant.VT_UI4:
            case JIVariant.VT_UINT:
                c = Integer.class;
                break;
            case JIVariant.VT_I8:
            case JIVariant.VT_INT:
                c = Long.class;
                break;
            case JIVariant.VT_R4:
                c = Float.class;
                break;
            case JIVariant.VT_R8:
                c = Double.class;
                break;
            case JIVariant.VT_DATE:
                c = Date.class;
                break;
            case JIVariant.VT_BSTR:
                c = String.class;
                break;
            case JIVariant.VT_ARRAY:
                c = itemState.getValue().getObjectAsArray().getArrayClass();
                break;
            case JIVariant.VT_BOOL:
                c = Boolean.class;
                break;
            default:
                c = String.class;
        }

        OPCLeafProperty retVal = new OPCLeafProperty(name, c, displayName, shortDescription, i, l, forceHWRead);
        return retVal;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return the forceHardwareRead
     */
    public boolean isForceHardwareRead() {
        return forceHardwareRead;
    }

    /**
     * @param forceHardwareRead the forceHardwareRead to set
     */
    public void setForceHardwareRead(boolean forceHardwareRead) {
        this.forceHardwareRead = forceHardwareRead;
    }

    @Override
    public String getShortDescription() {
        return "<html>"
                + "<ul>"
                + "<li>" + leaf.getItemId() + "</li>"
                + "<li>COM Type Code: <b>" + comDataType + "</b></li>"
                + "<li>" + getValueType().getName() + "</li></ul>"
                + "</html>";
    }

    @Override
    public T getValue() throws IllegalAccessException {
        try {
            final long start = System.currentTimeMillis();
            ItemState itemState = item.read(forceHardwareRead);
            final long end = System.currentTimeMillis();
            JIVariant jIVariant = itemState.getValue();
            int type = jIVariant.getType();
            if (type != comDataType) {
                comDataType = type;
                //Hack
                setShortDescription(getShortDescription());
            }


            //Logging this on the swing thread sucks... and there is no SLF async log method (that I can find)
            asyncLoggingThread.submit(new Runnable() {

                @Override
                public void run() {
                    logger.debug("Item {} : Readtime {} ms.", item.getId(), end - start);
                }
            });



            switch (type) {
                case JIVariant.VT_I1:
                    //This is a hack... shame on Vikram (Maybe MS doesnt have a byte?)
                    return (T) Byte.valueOf((byte) jIVariant.getObjectAsChar());
                case JIVariant.VT_I2:
                    return (T) Short.valueOf(jIVariant.getObjectAsShort());
                case JIVariant.VT_I4:
                    return (T) Integer.valueOf(jIVariant.getObjectAsInt());
                case JIVariant.VT_I8:
                case JIVariant.VT_INT:
                    return (T) Long.valueOf(jIVariant.getObjectAsInt());
                case JIVariant.VT_DATE:
                    return (T) jIVariant.getObjectAsDate();
                case JIVariant.VT_R4:
                    return (T) Float.valueOf(jIVariant.getObjectAsFloat());
                case JIVariant.VT_R8:
                    return (T) Double.valueOf(jIVariant.getObjectAsDouble());
                case JIVariant.VT_UI1:
                    return (T) Byte.valueOf(jIVariant.getObjectAsUnsigned().getValue().byteValue());
                case JIVariant.VT_UI2:
                    return (T) Short.valueOf(jIVariant.getObjectAsUnsigned().getValue().shortValue());
                case JIVariant.VT_UI4:
                case JIVariant.VT_UINT:
                    return (T) Integer.valueOf(jIVariant.getObjectAsUnsigned().getValue().intValue());
                case JIVariant.VT_BSTR:
                    return (T) String.valueOf(jIVariant.getObjectAsString2());
                case JIVariant.VT_ARRAY:
                    return (T) jIVariant.getObjectAsArray().getArrayInstance();
                case JIVariant.VT_BOOL:
                    return (T) Boolean.valueOf(jIVariant.getObjectAsBoolean());
                default:
                    return (T) jIVariant.getObject().toString();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    @Override
    public void setValue(T val) throws IllegalAccessException, IllegalArgumentException {

        JIVariant valToWrite = JIVariant.makeVariant(val);
        try {
            item.write(valToWrite);
        } catch (JIException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
