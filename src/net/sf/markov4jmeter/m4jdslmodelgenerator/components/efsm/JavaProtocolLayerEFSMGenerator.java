/***************************************************************************
 * Copyright (c) 2016 the WESSBAS project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


package net.sf.markov4jmeter.m4jdslmodelgenerator.components.efsm;

import java.util.ArrayList;

import wessbas.commons.parser.SessionData;

import m4jdsl.M4jdslFactory;
import m4jdsl.ProtocolExitState;
import m4jdsl.ProtocolLayerEFSM;
import m4jdsl.ProtocolState;
import m4jdsl.ProtocolTransition;
import m4jdsl.Request;
import net.sf.markov4jmeter.m4jdslmodelgenerator.GeneratorException;
import net.sf.markov4jmeter.m4jdslmodelgenerator.util.IdGenerator;

/**
 * Class for building Protocol Layer EFSMs based on Java requests.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class JavaProtocolLayerEFSMGenerator
extends AbstractProtocolLayerEFSMGenerator {


    /* ***************************  constructors  *************************** */

    /**
     * Constructor for a Protocol Layer EFSM with Java requests.
     *
     * @param m4jdslFactory
     *     instance for creating M4J-DSL model elements.
     * @param idGenerator
     *     instance for creating unique Protocol State IDs.
     * @param requestIdGenerator
     *     instance for creating unique request IDs.
     */
    public JavaProtocolLayerEFSMGenerator (
            final M4jdslFactory m4jdslFactory,
            final IdGenerator idGenerator,
            final IdGenerator requestIdGenerator,
            final ArrayList<SessionData> sessions) {

        super(m4jdslFactory, idGenerator, requestIdGenerator, sessions);
    }


    /* **************************  public methods  ************************** */


    @Override
    public ProtocolLayerEFSM generateProtocolLayerEFSM (
            final String serviceName) throws GeneratorException {

        final ProtocolLayerEFSM protocolLayerEFSM =
                this.createEmptyProtocolLayerEFSM();

        final ProtocolExitState protocolExitState =
                protocolLayerEFSM.getExitState();

        // TODO: more information required for building SUT-specific requests and transitions;

        // might throw a GeneratorException;
        final Request request = this.createRequest(
                AbstractProtocolLayerEFSMGenerator.REQUEST_TYPE_JAVA);

        final ProtocolState protocolState = this.createProtocolState(request);

        final String guard;  // no SUT-specific guard available yet ...
        final String action;  // no SUT-specific action available yet ...

        final ProtocolTransition protocolTransition =
                this.createProtocolTransition(
                        protocolExitState,
                        "<guard>",
                        "<action>");

        protocolState.getOutgoingTransitions().add(protocolTransition);

        protocolLayerEFSM.getProtocolStates().add(protocolState);
        protocolLayerEFSM.setInitialState(protocolState);

        return protocolLayerEFSM;
    }
}
