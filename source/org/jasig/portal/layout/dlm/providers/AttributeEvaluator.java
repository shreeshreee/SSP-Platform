
package org.jasig.portal.layout.dlm.providers;

import org.jasig.portal.layout.dlm.Evaluator;
import org.jasig.portal.security.IPerson;

public class AttributeEvaluator
    implements Evaluator
{
    public static final String RCS_ID = "@(#) $Header$";

    public static final int CONTAINS = 0;
    public static final int EQUALS = 1;
    public static final int STARTS_WITH = 2;
    public static final int ENDS_WITH = 3;
    public static final int EXISTS = 3;

    protected int mode = -1;
    protected String name = null;
    protected String value = null;

    public AttributeEvaluator( String name, String mode, String value )
        throws Exception
    {
        if ( mode.equals( "equals" ) )
        {
            this.mode = EQUALS;
            if ( value == null )
                throw new Exception( "Missing value attribute" +
                                     ". For mode of 'equals' value " +
                                     "must be defined" );
        }
        else if ( mode.equals( "exists" ) )
            this.mode = EXISTS;
        else if ( mode.equals( "contains" ) )
        {
            this.mode = CONTAINS;
            if ( value == null || value.equals( "" ) )
                throw new Exception( "Missing or invalid value attribute" +
                                     ". For mode of 'contains' value " +
                                     "must be defined and not empty" );
        }
        else if ( mode.equals( "startsWith" ) )
        {
            this.mode = STARTS_WITH;
            if ( value == null || value.equals( "" ) )
                throw new Exception( "Missing or invalid value attribute. " +
                                     "For mode of 'startsWith' value must " +
                                     "be defined and not empty" );
        }
        else if ( mode.equals( "endsWith" ) )
        {
            this.mode = ENDS_WITH;
            if ( value == null || value.equals( "" ) )
                throw new Exception( "Missing or invalid value attribute. " +
                                     "For mode of 'endsWith' value must be " +
                                     "defined and not empty" );
        }
        else
            throw new Exception( "Invalid mode attribute. Expected mode " +
                                 "of 'contains', 'equals', 'startsWith', " +
                                 "'exists', or 'endsWith'" );
            
        this.name = name;
        this.value = value;
    }
    public boolean isApplicable( IPerson p )
    {
        String attrib = (String) p.getAttribute( name );

        // for tests other than 'exists' the attribute must be defined
        if ( attrib == null && mode != EXISTS )
            return false;

        if ( mode == EQUALS )
            return attrib.equals( value );
        if ( mode == EXISTS )
            return attrib != null;
        if ( mode == STARTS_WITH )
            return attrib.startsWith( value );
        if ( mode == ENDS_WITH )
            return attrib.endsWith( value );
        if ( mode == CONTAINS )
            return (attrib.indexOf( value ) != -1 );
        // will never get here
        return false;
    }
}