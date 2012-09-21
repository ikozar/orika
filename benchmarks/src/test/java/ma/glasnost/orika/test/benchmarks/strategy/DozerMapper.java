/*
 * Orika - simpler, better and faster Java bean mapping
 * 
 * Copyright (C) 2011 Orika authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ma.glasnost.orika.test.benchmarks.strategy;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


/**
 *
 */
public class DozerMapper<S, D> implements MappingProvider<S, D> {

    private Mapper mapper;
    
    public void initialize() {
        this.mapper = new DozerBeanMapper();
        
    }
    
    /* (non-Javadoc)
     * @see ma.glasnost.orika.test.benchmarks.strategy.MappingProvider#mapTo(java.lang.Object)
     */
    @Override
    public D mapTo(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /* (non-Javadoc)
     * @see ma.glasnost.orika.test.benchmarks.strategy.MappingProvider#mapFrom(java.lang.Object)
     */
    @Override
    public S mapFrom(D destination,  Class<S> sourceClass) {
        return mapper.map(destination, sourceClass);
    }

    /* (non-Javadoc)
     * @see ma.glasnost.orika.test.benchmarks.strategy.MappingProvider#mapTo(java.lang.Object, java.lang.Object)
     */
    @Override
    public void mapTo(S source, D destination) {
        mapper.map(source, destination);
    }

    /* (non-Javadoc)
     * @see ma.glasnost.orika.test.benchmarks.strategy.MappingProvider#mapFrom(java.lang.Object, java.lang.Object)
     */
    @Override
    public void mapFrom(D destination, S source) {
        mapper.map(destination, source); 
    }
}
