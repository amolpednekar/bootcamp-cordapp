package java_bootcamp;

import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.transactions.LedgerTransaction;

import static net.corda.core.contracts.ContractsDSL.requireThat;

/* Our contract, governing how our state will evolve over time.
 * See src/main/kotlin/examples/ExampleContract.java for an example. */
public class TokenContract implements Contract{
    public static String ID = "java_bootcamp.TokenContract";

    @Override
    public void verify(LedgerTransaction tx) throws IllegalArgumentException {
        if(tx.getInputs().size()!=0) throw new IllegalArgumentException("Tx inputs are not empty");
        if(tx.getOutputs().size()!=1) throw new IllegalArgumentException("Tx has more than one output");
        if(tx.getCommands().size()!=1) throw new IllegalArgumentException("Tx does not have ONE command");

        if(!(tx.getOutput(0) instanceof TokenState)) throw new IllegalArgumentException(("Tx output is not an instance of token state"));

//        TokenState ts1 = tx.findOutput(TokenState.class, state -> state.getAmount()>0);
//
//        if(ts1 == null ) throw new IllegalArgumentException("Amount is not positive");


        TokenState ts2 = (TokenState) tx.getOutput(0);
        if( ts2.getAmount() <= 0 ) throw new IllegalArgumentException("Amount is not positive");

        Command cmd = tx.getCommand(0);

        if(!(cmd.getValue() instanceof TokenContract.Issue) ) throw new IllegalArgumentException("Command is not issue");

        if(!(cmd.getSigners().contains(ts2.getIssuer().getOwningKey()))) throw new IllegalArgumentException("Issue didnt sign!");
    }


    public static class Issue implements CommandData {}
}