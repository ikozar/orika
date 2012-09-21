/*
 * This code is distributed under The GNU Lesser General Public License (LGPLv3)
 * Please visit GNU site for LGPLv3 http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright Denis Pavlov 2009
 * Web: http://www.inspire-software.com
 * SVN: https://geda-genericdto.svn.sourceforge.net/svnroot/geda-genericdto
 */

package com.inspiresoftware.lib.dto.geda.benchmark.support.orika;

import ma.glasnost.orika.DedicatedMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;

import com.inspiresoftware.lib.dto.geda.benchmark.Mapper;
import com.inspiresoftware.lib.dto.geda.benchmark.domain.Address;
import com.inspiresoftware.lib.dto.geda.benchmark.domain.Person;
import com.inspiresoftware.lib.dto.geda.benchmark.dto.AddressDTO;
import com.inspiresoftware.lib.dto.geda.benchmark.dto.PersonDTO;

/**
 * .
 * <p/>
 * User: denispavlov
 * Date: Sep 17, 2012
 * Time: 12:04:50 PM
 */
public class OrikaMapper implements Mapper {

    private MapperFacade mapperFacade;
    private DedicatedMapperFacade<Person, PersonDTO> mapper;
    
    public OrikaMapper() {
        final MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.registerClassMap(factory.classMap(Address.class, AddressDTO.class).
            field("addressLine1", "addressLine1").
            field("addressLine2", "addressLine2").
            field("postCode", "postCode").
            field("city", "city").
            field("country.name", "countryName").
            toClassMap()
        );
        factory.registerClassMap(factory.classMap(Person.class, PersonDTO.class).
            field("name.firstname", "firstName").
            field("name.surname", "lastName").
            field("currentAddress", "currentAddress").
            toClassMap()
        );
        this.mapperFacade = factory.getMapperFacade();
        this.mapper = mapperFacade.dedicatedMapperFor(
                TypeFactory.valueOf(Person.class), 
                TypeFactory.valueOf(PersonDTO.class), true);
    }

    public Object fromEntity(final Object entity) {
        PersonDTO dto = new PersonDTO();
        mapper.mapAtoB((Person) entity, dto);
        return dto;
    }

    public Object fromDto(final Object dto) {
        Person entity = new Person();
        mapper.mapBtoA((PersonDTO) dto, entity);
        return entity;
    }
}
