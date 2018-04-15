package java_bootcamp;

import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/* Our state, defining a shared fact on the ledger.
 * See src/main/kotlin/examples/IAmAState.java and
 * src/main/kotlin/examples/IAmAlsoAState.java for examples. */
public class TokenState implements ContractState {  // implement ContractState to make it a state

    private Party issuer;   //node identities are of type Party
    private Party recipient;
    private int amount;

    public TokenState(Party issuer, Party recipient, int amount) {
        this.issuer = issuer;
        this.recipient = recipient;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Party getIssuer() {
        return issuer;
    }

    public Party getRecipient() {
        return recipient;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return ImmutableList.of(issuer, recipient); // people who are notified about updates to this state
    }

}