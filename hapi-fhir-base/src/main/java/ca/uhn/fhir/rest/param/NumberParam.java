package ca.uhn.fhir.rest.param;

/*
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 - 2016 University Health Network
 * %%
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
 * #L%
 */
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.model.api.IQueryParameterType;

public class NumberParam extends BaseParamWithPrefix<NumberParam> implements IQueryParameterType {

	private BigDecimal myQuantity;

	/**
	 * Constructor
	 */
	public NumberParam() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param theValue
	 *            A string value, e.g. "&gt;5.0"
	 */
	public NumberParam(String theValue) {
		setValueAsQueryToken(null, theValue);
	}

	@Override
	String doGetQueryParameterQualifier() {
		return null;
	}

	@Override
	String doGetValueAsQueryToken(FhirContext theContext) {
		StringBuilder b = new StringBuilder();
		if (getPrefix() != null) {
			b.append(ParameterUtil.escapeWithDefault(getPrefix().getValueForContext(theContext)));
		}
		b.append(ParameterUtil.escapeWithDefault(myQuantity.toPlainString()));
		return b.toString();
	}
	
	@Override
	void doSetValueAsQueryToken(String theQualifier, String theValue) {
		if (getMissing() != null && isBlank(theValue)) {
			return;
		}
		String value = super.extractPrefixAndReturnRest(theValue);
		myQuantity = null;
		if (isNotBlank(value)) {
			myQuantity = new BigDecimal(value);
		}
	}
	
	
	@Override
	public String toString() {
		ToStringBuilder b = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		b.append("prefix", getPrefix());
		b.append("value", myQuantity);
		return b.build();
	}

	public BigDecimal getValue() {
		return myQuantity;
	}
	
	public NumberParam setValue(BigDecimal theValue) {
		myQuantity = theValue;
		return this;
	}

}
