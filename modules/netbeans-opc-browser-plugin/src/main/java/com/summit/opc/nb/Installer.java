/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import org.jinterop.dcom.common.JISystem;
import org.openide.modules.ModuleInstall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Installer extends ModuleInstall {
Logger logger = LoggerFactory.getLogger(Installer.class);
    
    @Override
    public void restored() {
        logger.info("Silencing JUL logging...");
        
        JISystem.getLogger().setUseParentHandlers(false);
        //SLF4JBridgeHandler.install();
    }
}
