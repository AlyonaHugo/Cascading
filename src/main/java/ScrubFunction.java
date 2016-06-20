/**
 * Created by opolishchuk on 20.06.2016.
 */

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;


public class ScrubFunction extends BaseOperation implements Function
{
    public ScrubFunction( Fields fieldDeclaration )
    {
        super( 2, fieldDeclaration );
    }

    public void operate( FlowProcess flowProcess, FunctionCall functionCall )
    {
        TupleEntry argument = functionCall.getArguments();
        String doc_id = argument.getString( 0 );
        String token = scrubText( argument.getString( 1 ) );

        if( token != null )
        {
            Tuple result = new Tuple();
            result.add( doc_id );
            result.add( token );
            functionCall.getOutputCollector().add( result );
        }
    }

    public String scrubText( String text )
    {
        if(text.trim().toLowerCase().equals("shakespeare")){
            return text.trim().toLowerCase();
        }
        return  null;
    }
}
