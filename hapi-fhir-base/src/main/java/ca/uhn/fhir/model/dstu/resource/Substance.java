















package ca.uhn.fhir.model.dstu.resource;


import java.util.*;
import ca.uhn.fhir.model.api.*;
import ca.uhn.fhir.model.api.annotation.*;

import ca.uhn.fhir.model.dstu.composite.AddressDt;
import ca.uhn.fhir.model.dstu.valueset.AdministrativeGenderCodesEnum;
import ca.uhn.fhir.model.dstu.valueset.AdmitSourceEnum;
import ca.uhn.fhir.model.dstu.valueset.AggregationModeEnum;
import ca.uhn.fhir.model.dstu.valueset.AnimalSpeciesEnum;
import ca.uhn.fhir.model.dstu.composite.AttachmentDt;
import ca.uhn.fhir.model.dstu.valueset.BindingConformanceEnum;
import ca.uhn.fhir.model.dstu.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu.composite.CodingDt;
import ca.uhn.fhir.model.dstu.valueset.ConformanceEventModeEnum;
import ca.uhn.fhir.model.dstu.valueset.ConformanceStatementStatusEnum;
import ca.uhn.fhir.model.dstu.valueset.ConstraintSeverityEnum;
import ca.uhn.fhir.model.dstu.composite.ContactDt;
import ca.uhn.fhir.model.dstu.valueset.DataTypeEnum;
import ca.uhn.fhir.model.dstu.resource.Device;
import ca.uhn.fhir.model.dstu.resource.DiagnosticOrder;
import ca.uhn.fhir.model.dstu.valueset.DiagnosticOrderPriorityEnum;
import ca.uhn.fhir.model.dstu.valueset.DiagnosticOrderStatusEnum;
import ca.uhn.fhir.model.dstu.valueset.DiagnosticReportStatusEnum;
import ca.uhn.fhir.model.dstu.valueset.DocumentModeEnum;
import ca.uhn.fhir.model.dstu.resource.Encounter;
import ca.uhn.fhir.model.dstu.valueset.EncounterClassEnum;
import ca.uhn.fhir.model.dstu.valueset.EncounterReasonCodesEnum;
import ca.uhn.fhir.model.dstu.valueset.EncounterStateEnum;
import ca.uhn.fhir.model.dstu.valueset.EncounterTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.ExtensionContextEnum;
import ca.uhn.fhir.model.dstu.valueset.FHIRDefinedTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.FilterOperatorEnum;
import ca.uhn.fhir.model.dstu.resource.Group;
import ca.uhn.fhir.model.dstu.valueset.GroupTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.HierarchicalRelationshipTypeEnum;
import ca.uhn.fhir.model.dstu.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu.valueset.ImagingModalityEnum;
import ca.uhn.fhir.model.dstu.resource.ImagingStudy;
import ca.uhn.fhir.model.dstu.valueset.InstanceAvailabilityEnum;
import ca.uhn.fhir.model.dstu.valueset.LinkTypeEnum;
import ca.uhn.fhir.model.dstu.resource.Location;
import ca.uhn.fhir.model.dstu.valueset.LocationModeEnum;
import ca.uhn.fhir.model.dstu.valueset.LocationStatusEnum;
import ca.uhn.fhir.model.dstu.valueset.LocationTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.MaritalStatusCodesEnum;
import ca.uhn.fhir.model.dstu.resource.Media;
import ca.uhn.fhir.model.dstu.valueset.MediaTypeEnum;
import ca.uhn.fhir.model.dstu.resource.Medication;
import ca.uhn.fhir.model.dstu.valueset.MedicationKindEnum;
import ca.uhn.fhir.model.dstu.valueset.MessageEventEnum;
import ca.uhn.fhir.model.dstu.valueset.MessageSignificanceCategoryEnum;
import ca.uhn.fhir.model.dstu.valueset.MessageTransportEnum;
import ca.uhn.fhir.model.dstu.valueset.ModalityEnum;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.dstu.valueset.ObservationInterpretationCodesEnum;
import ca.uhn.fhir.model.dstu.valueset.ObservationRelationshipTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.ObservationReliabilityEnum;
import ca.uhn.fhir.model.dstu.valueset.ObservationStatusEnum;
import ca.uhn.fhir.model.dstu.resource.Organization;
import ca.uhn.fhir.model.dstu.valueset.OrganizationTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.ParticipantTypeEnum;
import ca.uhn.fhir.model.dstu.resource.Patient;
import ca.uhn.fhir.model.dstu.valueset.PatientRelationshipTypeEnum;
import ca.uhn.fhir.model.dstu.composite.PeriodDt;
import ca.uhn.fhir.model.dstu.resource.Practitioner;
import ca.uhn.fhir.model.dstu.valueset.PractitionerRoleEnum;
import ca.uhn.fhir.model.dstu.valueset.PractitionerSpecialtyEnum;
import ca.uhn.fhir.model.dstu.resource.Profile;
import ca.uhn.fhir.model.dstu.valueset.PropertyRepresentationEnum;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.composite.RangeDt;
import ca.uhn.fhir.model.dstu.composite.RatioDt;
import ca.uhn.fhir.model.dstu.resource.RelatedPerson;
import ca.uhn.fhir.model.dstu.valueset.ResourceProfileStatusEnum;
import ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu.valueset.ResourceTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.RestfulConformanceModeEnum;
import ca.uhn.fhir.model.dstu.valueset.RestfulOperationSystemEnum;
import ca.uhn.fhir.model.dstu.valueset.RestfulOperationTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.RestfulSecurityServiceEnum;
import ca.uhn.fhir.model.dstu.composite.SampledDataDt;
import ca.uhn.fhir.model.dstu.valueset.SearchParamTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.SlicingRulesEnum;
import ca.uhn.fhir.model.dstu.resource.Specimen;
import ca.uhn.fhir.model.dstu.valueset.SpecimenCollectionMethodEnum;
import ca.uhn.fhir.model.dstu.valueset.SpecimenTreatmentProcedureEnum;
import ca.uhn.fhir.model.dstu.resource.Substance;
import ca.uhn.fhir.model.dstu.valueset.SubstanceTypeEnum;
import ca.uhn.fhir.model.dstu.resource.ValueSet;
import ca.uhn.fhir.model.dstu.valueset.ValueSetStatusEnum;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu.composite.DurationDt;
import ca.uhn.fhir.model.primitive.Base64BinaryDt;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
import ca.uhn.fhir.model.primitive.BoundCodeableConceptDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.DecimalDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.primitive.OidDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.model.primitive.UriDt;


/**
 * HAPI/FHIR <b>Substance</b> Resource
 * (A homogeneous material with a definite composition)
 *
 * <p>
 * <b>Definition:</b>
 * 
 * </p> 
 *
 * <p>
 * <b>Requirements:</b>
 * 
 * </p> 
 */
@ResourceDef(name="Substance", profile="http://hl7.org/fhir/profiles/Substance", id="substance")
public class Substance extends BaseResource implements IResource {

	/**
	 * Search parameter constant for <b>type</b>
	 * <p>
	 * Description: <b>The type of the substance</b><br/>
	 * Type: <b>token</b><br/>
	 * Path: <b>Substance.type</b><br/>
	 * </p>
	 */
	public static final String SP_TYPE = "type";

	/**
	 * Search parameter constant for <b>identifier</b>
	 * <p>
	 * Description: <b></b><br/>
	 * Type: <b>token</b><br/>
	 * Path: <b>Substance.instance.identifier</b><br/>
	 * </p>
	 */
	public static final String SP_IDENTIFIER = "identifier";

	/**
	 * Search parameter constant for <b>expiry</b>
	 * <p>
	 * Description: <b></b><br/>
	 * Type: <b>date</b><br/>
	 * Path: <b>Substance.instance.expiry</b><br/>
	 * </p>
	 */
	public static final String SP_EXPIRY = "expiry";

	/**
	 * Search parameter constant for <b>quantity</b>
	 * <p>
	 * Description: <b></b><br/>
	 * Type: <b>number</b><br/>
	 * Path: <b>Substance.instance.quantity</b><br/>
	 * </p>
	 */
	public static final String SP_QUANTITY = "quantity";

	/**
	 * Search parameter constant for <b>substance</b>
	 * <p>
	 * Description: <b></b><br/>
	 * Type: <b>reference</b><br/>
	 * Path: <b>Substance.ingredient.substance</b><br/>
	 * </p>
	 */
	public static final String SP_SUBSTANCE = "substance";


	@Child(name="type", type=CodeableConceptDt.class, order=0, min=1, max=1)	
	@Description(
		shortDefinition="What kind of substance this is",
		formalDefinition="A code (or set of codes) that identify this substance"
	)
	private BoundCodeableConceptDt<SubstanceTypeEnum> myType;
	
	@Child(name="description", type=StringDt.class, order=1, min=0, max=1)	
	@Description(
		shortDefinition="Textual description of the substance, comments",
		formalDefinition="A description of the substance - its appearance, handling requirements, and other usage notes"
	)
	private StringDt myDescription;
	
	@Child(name="instance", order=2, min=0, max=1)	
	@Description(
		shortDefinition="If this describes a specific package/container of the substance",
		formalDefinition="Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance"
	)
	private Instance myInstance;
	
	@Child(name="ingredient", order=3, min=0, max=Child.MAX_UNLIMITED)	
	@Description(
		shortDefinition="Composition information about the substance",
		formalDefinition="A substance can be composed of other substances"
	)
	private java.util.List<Ingredient> myIngredient;
	

	@Override
	public boolean isEmpty() {
		return super.isBaseEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(  myType,  myDescription,  myInstance,  myIngredient);
	}
	
	@Override
	public java.util.List<IElement> getAllPopulatedChildElements() {
		return getAllPopulatedChildElementsOfType(null);
	}

	@Override
	public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
		return ca.uhn.fhir.util.ElementUtil.allPopulatedChildElements(theType, myType, myDescription, myInstance, myIngredient);
	}

	/**
	 * Gets the value(s) for <b>type</b> (What kind of substance this is).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * A code (or set of codes) that identify this substance
     * </p> 
	 */
	public BoundCodeableConceptDt<SubstanceTypeEnum> getType() {  
		if (myType == null) {
			myType = new BoundCodeableConceptDt<SubstanceTypeEnum>(SubstanceTypeEnum.VALUESET_BINDER);
		}
		return myType;
	}

	/**
	 * Sets the value(s) for <b>type</b> (What kind of substance this is)
	 *
     * <p>
     * <b>Definition:</b>
     * A code (or set of codes) that identify this substance
     * </p> 
	 */
	public void setType(BoundCodeableConceptDt<SubstanceTypeEnum> theValue) {
		myType = theValue;
	}


	/**
	 * Sets the value(s) for <b>type</b> (What kind of substance this is)
	 *
     * <p>
     * <b>Definition:</b>
     * A code (or set of codes) that identify this substance
     * </p> 
	 */
	public void setType(SubstanceTypeEnum theValue) {
		getType().setValueAsEnum(theValue);
	}

  
	/**
	 * Gets the value(s) for <b>description</b> (Textual description of the substance, comments).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * A description of the substance - its appearance, handling requirements, and other usage notes
     * </p> 
	 */
	public StringDt getDescription() {  
		if (myDescription == null) {
			myDescription = new StringDt();
		}
		return myDescription;
	}

	/**
	 * Sets the value(s) for <b>description</b> (Textual description of the substance, comments)
	 *
     * <p>
     * <b>Definition:</b>
     * A description of the substance - its appearance, handling requirements, and other usage notes
     * </p> 
	 */
	public void setDescription(StringDt theValue) {
		myDescription = theValue;
	}


 	/**
	 * Sets the value for <b>description</b> (Textual description of the substance, comments)
	 *
     * <p>
     * <b>Definition:</b>
     * A description of the substance - its appearance, handling requirements, and other usage notes
     * </p> 
	 */
	public void setDescription( String theString) {
		myDescription = new StringDt(theString); 
	}

 
	/**
	 * Gets the value(s) for <b>instance</b> (If this describes a specific package/container of the substance).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance
     * </p> 
	 */
	public Instance getInstance() {  
		if (myInstance == null) {
			myInstance = new Instance();
		}
		return myInstance;
	}

	/**
	 * Sets the value(s) for <b>instance</b> (If this describes a specific package/container of the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance
     * </p> 
	 */
	public void setInstance(Instance theValue) {
		myInstance = theValue;
	}


  
	/**
	 * Gets the value(s) for <b>ingredient</b> (Composition information about the substance).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * A substance can be composed of other substances
     * </p> 
	 */
	public java.util.List<Ingredient> getIngredient() {  
		if (myIngredient == null) {
			myIngredient = new java.util.ArrayList<Ingredient>();
		}
		return myIngredient;
	}

	/**
	 * Sets the value(s) for <b>ingredient</b> (Composition information about the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * A substance can be composed of other substances
     * </p> 
	 */
	public void setIngredient(java.util.List<Ingredient> theValue) {
		myIngredient = theValue;
	}

	/**
	 * Adds and returns a new value for <b>ingredient</b> (Composition information about the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * A substance can be composed of other substances
     * </p> 
	 */
	public Ingredient addIngredient() {
		Ingredient newType = new Ingredient();
		getIngredient().add(newType);
		return newType; 
	}

  
	/**
	 * Block class for child element: <b>Substance.instance</b> (If this describes a specific package/container of the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance
     * </p> 
	 */
	@Block(name="Substance.instance")	
	public static class Instance extends BaseElement implements IResourceBlock {
	
	@Child(name="identifier", type=IdentifierDt.class, order=0, min=0, max=1)	
	@Description(
		shortDefinition="Identifier of the package/container",
		formalDefinition="Identifier associated with the package/container (usually a label affixed directly)"
	)
	private IdentifierDt myIdentifier;
	
	@Child(name="expiry", type=DateTimeDt.class, order=1, min=0, max=1)	
	@Description(
		shortDefinition="When no longer valid to use",
		formalDefinition="When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry."
	)
	private DateTimeDt myExpiry;
	
	@Child(name="quantity", type=QuantityDt.class, order=2, min=0, max=1)	
	@Description(
		shortDefinition="Amount of substance in the package",
		formalDefinition="The amount of the substance"
	)
	private QuantityDt myQuantity;
	

	@Override
	public boolean isEmpty() {
		return super.isBaseEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(  myIdentifier,  myExpiry,  myQuantity);
	}
	
	@Override
	public java.util.List<IElement> getAllPopulatedChildElements() {
		return getAllPopulatedChildElementsOfType(null);
	}

	@Override
	public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
		return ca.uhn.fhir.util.ElementUtil.allPopulatedChildElements(theType, myIdentifier, myExpiry, myQuantity);
	}

	/**
	 * Gets the value(s) for <b>identifier</b> (Identifier of the package/container).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Identifier associated with the package/container (usually a label affixed directly)
     * </p> 
	 */
	public IdentifierDt getIdentifier() {  
		if (myIdentifier == null) {
			myIdentifier = new IdentifierDt();
		}
		return myIdentifier;
	}

	/**
	 * Sets the value(s) for <b>identifier</b> (Identifier of the package/container)
	 *
     * <p>
     * <b>Definition:</b>
     * Identifier associated with the package/container (usually a label affixed directly)
     * </p> 
	 */
	public void setIdentifier(IdentifierDt theValue) {
		myIdentifier = theValue;
	}


  
	/**
	 * Gets the value(s) for <b>expiry</b> (When no longer valid to use).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
     * </p> 
	 */
	public DateTimeDt getExpiry() {  
		if (myExpiry == null) {
			myExpiry = new DateTimeDt();
		}
		return myExpiry;
	}

	/**
	 * Sets the value(s) for <b>expiry</b> (When no longer valid to use)
	 *
     * <p>
     * <b>Definition:</b>
     * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
     * </p> 
	 */
	public void setExpiry(DateTimeDt theValue) {
		myExpiry = theValue;
	}


 	/**
	 * Sets the value for <b>expiry</b> (When no longer valid to use)
	 *
     * <p>
     * <b>Definition:</b>
     * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
     * </p> 
	 */
	public void setExpiry( Date theDate,  TemporalPrecisionEnum thePrecision) {
		myExpiry = new DateTimeDt(theDate, thePrecision); 
	}

	/**
	 * Sets the value for <b>expiry</b> (When no longer valid to use)
	 *
     * <p>
     * <b>Definition:</b>
     * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
     * </p> 
	 */
	public void setExpiryWithSecondsPrecision( Date theDate) {
		myExpiry = new DateTimeDt(theDate); 
	}

 
	/**
	 * Gets the value(s) for <b>quantity</b> (Amount of substance in the package).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * The amount of the substance
     * </p> 
	 */
	public QuantityDt getQuantity() {  
		if (myQuantity == null) {
			myQuantity = new QuantityDt();
		}
		return myQuantity;
	}

	/**
	 * Sets the value(s) for <b>quantity</b> (Amount of substance in the package)
	 *
     * <p>
     * <b>Definition:</b>
     * The amount of the substance
     * </p> 
	 */
	public void setQuantity(QuantityDt theValue) {
		myQuantity = theValue;
	}


  

	}


	/**
	 * Block class for child element: <b>Substance.ingredient</b> (Composition information about the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * A substance can be composed of other substances
     * </p> 
	 */
	@Block(name="Substance.ingredient")	
	public static class Ingredient extends BaseElement implements IResourceBlock {
	
	@Child(name="quantity", type=RatioDt.class, order=0, min=0, max=1)	
	@Description(
		shortDefinition="Optional amount (concentration)",
		formalDefinition="The amount of the ingredient in the substance - a concentration ratio"
	)
	private RatioDt myQuantity;
	
	@Child(name="substance", order=1, min=1, max=1, type={
		ca.uhn.fhir.model.dstu.resource.Substance.class,
	})
	@Description(
		shortDefinition="A component of the substance",
		formalDefinition="Another substance that is a component of this substance"
	)
	private ResourceReferenceDt mySubstance;
	

	@Override
	public boolean isEmpty() {
		return super.isBaseEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(  myQuantity,  mySubstance);
	}
	
	@Override
	public java.util.List<IElement> getAllPopulatedChildElements() {
		return getAllPopulatedChildElementsOfType(null);
	}

	@Override
	public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
		return ca.uhn.fhir.util.ElementUtil.allPopulatedChildElements(theType, myQuantity, mySubstance);
	}

	/**
	 * Gets the value(s) for <b>quantity</b> (Optional amount (concentration)).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * The amount of the ingredient in the substance - a concentration ratio
     * </p> 
	 */
	public RatioDt getQuantity() {  
		if (myQuantity == null) {
			myQuantity = new RatioDt();
		}
		return myQuantity;
	}

	/**
	 * Sets the value(s) for <b>quantity</b> (Optional amount (concentration))
	 *
     * <p>
     * <b>Definition:</b>
     * The amount of the ingredient in the substance - a concentration ratio
     * </p> 
	 */
	public void setQuantity(RatioDt theValue) {
		myQuantity = theValue;
	}


  
	/**
	 * Gets the value(s) for <b>substance</b> (A component of the substance).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Another substance that is a component of this substance
     * </p> 
	 */
	public ResourceReferenceDt getSubstance() {  
		if (mySubstance == null) {
			mySubstance = new ResourceReferenceDt();
		}
		return mySubstance;
	}

	/**
	 * Sets the value(s) for <b>substance</b> (A component of the substance)
	 *
     * <p>
     * <b>Definition:</b>
     * Another substance that is a component of this substance
     * </p> 
	 */
	public void setSubstance(ResourceReferenceDt theValue) {
		mySubstance = theValue;
	}


  

	}




}