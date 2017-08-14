/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.goeuro.brc;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author felix
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("de.goeuro.brc");
    }
    
}
