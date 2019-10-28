package org.kogito.ddoyle.reproducer;

import org.drools.compiler.lang.DRL5Expressions.literal_return;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

/**
 * SimpleSessionTest
 */
public class SimpleSessionTest {

    @Test
    public void testSession() throws InstantiationException, IllegalAccessException {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        EntryPoint ep = kieSession.getEntryPoint("Transactions");
        
        FactType simpleTxnFactType = kieSession.getKieBase().getFactType("org.kogito.ddoyle.reproducer", "SimpleTxn");
        Object simpleTxn = simpleTxnFactType.newInstance();
        simpleTxnFactType.set(simpleTxn, "text", "Hello");
        simpleTxnFactType.set(simpleTxn, "txnTS", 1);

        ep.insert(simpleTxn);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
    
}