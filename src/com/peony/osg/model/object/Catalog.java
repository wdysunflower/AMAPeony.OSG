package com.peony.osg.model.object;

import com.peony.osg.OSGApplication;
import com.peony.osg.model.base.ModelBase;

import java.io.Serializable;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class Catalog extends ModelBase implements Serializable {


    /**
     * Constructor
     *
     * @param titleRes
     */
    public Catalog(int titleRes) {
        super(titleRes);
    }

    public String getFilename()
    {
        String resStr = OSGApplication.appResource.getResourceEntryName(mTitleRes);
        return resStr + ".txt";
    }
}
