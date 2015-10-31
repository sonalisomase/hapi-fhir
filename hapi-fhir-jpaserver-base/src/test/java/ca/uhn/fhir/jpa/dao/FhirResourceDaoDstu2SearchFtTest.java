package ca.uhn.fhir.jpa.dao;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hl7.fhir.instance.model.api.IIdType;
import org.junit.Test;

import ca.uhn.fhir.model.dstu2.resource.Device;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.StringOrListParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.Constants;

public class FhirResourceDaoDstu2SearchFtTest extends BaseJpaDstu2Test {
	
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(FhirResourceDaoDstu2SearchFtTest.class);
	
	@Test
	public void testSearchAndReindex() {
		Patient patient;
		SearchParameterMap map;

		patient = new Patient();
		patient.getText().setDiv("<div>DIVAAA</div>");
		patient.addName().addGiven("NAMEAAA");
		IIdType pId1 = myPatientDao.create(patient).getId().toUnqualifiedVersionless();

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_TEXT, new StringParam("DIVAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		/*
		 * Reindex
		 */

		patient = new Patient();
		patient.setId(pId1);
		patient.getText().setDiv("<div>DIVBBB</div>");
		patient.addName().addGiven("NAMEBBB");
		myPatientDao.update(patient);

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), empty());

		map = new SearchParameterMap();
		map.add(Patient.SP_NAME, new StringParam("NAMEBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_TEXT, new StringParam("DIVBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

	}

	@Test
	public void testEverythingInstanceWithContentFilter() {
		Patient pt1 = new Patient();
		pt1.addName().addFamily("Everything").addGiven("Arthur");
		IIdType ptId1 = myPatientDao.create(pt1).getId().toUnqualifiedVersionless();

		Patient pt2 = new Patient();
		pt2.addName().addFamily("Everything").addGiven("Arthur");
		IIdType ptId2 = myPatientDao.create(pt2).getId().toUnqualifiedVersionless();

		Device dev1 = new Device();
		dev1.setManufacturer("Some Manufacturer");
		IIdType devId1 = myDeviceDao.create(dev1).getId().toUnqualifiedVersionless();
		
		Device dev2 = new Device();
		dev2.setManufacturer("Some Manufacturer 2");
		myDeviceDao.create(dev2).getId().toUnqualifiedVersionless();

		Observation obs1 = new Observation();
		obs1.getText().setDiv("<div>OBSTEXT1</div>");
		obs1.getSubject().setReference(ptId1);
		obs1.getCode().addCoding().setCode("CODE1");
		obs1.setValue(new StringDt("obsvalue1"));
		obs1.getDevice().setReference(devId1);
		IIdType obsId1 = myObservationDao.create(obs1).getId().toUnqualifiedVersionless();

		Observation obs2 = new Observation();
		obs2.getSubject().setReference(ptId1);
		obs2.getCode().addCoding().setCode("CODE2");
		obs2.setValue(new StringDt("obsvalue2"));
		IIdType obsId2 = myObservationDao.create(obs2).getId().toUnqualifiedVersionless();

		Observation obs3 = new Observation();
		obs3.getSubject().setReference(ptId2);
		obs3.getCode().addCoding().setCode("CODE3");
		obs3.setValue(new StringDt("obsvalue3"));
		IIdType obsId3 = myObservationDao.create(obs3).getId().toUnqualifiedVersionless();
		
		HttpServletRequest request;
		List<IIdType> actual;
		request = mock(HttpServletRequest.class);
		StringAndListParam param;
		
		ourLog.info("Pt1:{} Pt2:{} Obs1:{} Obs2:{} Obs3:{}", new Object[] {ptId1.getIdPart(), ptId2.getIdPart(), obsId1.getIdPart(), obsId2.getIdPart(), obsId3.getIdPart()});
		
		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientInstanceEverything(request, ptId1, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, devId1));

		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obstext1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientInstanceEverything(request, ptId1, null, null, null, null, param));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, devId1));

		request = mock(HttpServletRequest.class);
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientInstanceEverything(request, ptId1, null, null, null, null, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, obsId2, devId1));

		/*
		 * Add another match
		 */
		
		Observation obs4 = new Observation();
		obs4.getSubject().setReference(ptId1);
		obs4.getCode().addCoding().setCode("CODE1");
		obs4.setValue(new StringDt("obsvalue1"));
		IIdType obsId4 = myObservationDao.create(obs4).getId().toUnqualifiedVersionless();
		assertNotEquals(obsId1.getIdPart(), obsId4.getIdPart(), devId1);

		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientInstanceEverything(request, ptId1, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, obsId4, devId1));

		/*
		 * Make one previous match no longer match
		 */
		
		obs1 = new Observation();
		obs1.setId(obsId1);
		obs1.getSubject().setReference(ptId1);
		obs1.getCode().addCoding().setCode("CODE2");
		obs1.setValue(new StringDt("obsvalue2"));
		myObservationDao.update(obs1);

		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientInstanceEverything(request, ptId1, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId4));

	}
	
	@Test
	public void testEverythingTypeWithContentFilter() {
		Patient pt1 = new Patient();
		pt1.addName().addFamily("Everything").addGiven("Arthur");
		IIdType ptId1 = myPatientDao.create(pt1).getId().toUnqualifiedVersionless();

		Patient pt2 = new Patient();
		pt2.addName().addFamily("Everything").addGiven("Arthur");
		IIdType ptId2 = myPatientDao.create(pt2).getId().toUnqualifiedVersionless();

		Device dev1 = new Device();
		dev1.setManufacturer("Some Manufacturer");
		IIdType devId1 = myDeviceDao.create(dev1).getId().toUnqualifiedVersionless();
		
		Device dev2 = new Device();
		dev2.setManufacturer("Some Manufacturer 2");
		IIdType devId2 = myDeviceDao.create(dev2).getId().toUnqualifiedVersionless();

		Observation obs1 = new Observation();
		obs1.getSubject().setReference(ptId1);
		obs1.getCode().addCoding().setCode("CODE1");
		obs1.setValue(new StringDt("obsvalue1"));
		obs1.getDevice().setReference(devId1);
		IIdType obsId1 = myObservationDao.create(obs1).getId().toUnqualifiedVersionless();

		Observation obs2 = new Observation();
		obs2.getSubject().setReference(ptId1);
		obs2.getCode().addCoding().setCode("CODE2");
		obs2.setValue(new StringDt("obsvalue2"));
		IIdType obsId2 = myObservationDao.create(obs2).getId().toUnqualifiedVersionless();

		Observation obs3 = new Observation();
		obs3.getSubject().setReference(ptId2);
		obs3.getCode().addCoding().setCode("CODE3");
		obs3.setValue(new StringDt("obsvalue3"));
		IIdType obsId3 = myObservationDao.create(obs3).getId().toUnqualifiedVersionless();
		
		HttpServletRequest request;
		List<IIdType> actual;
		request = mock(HttpServletRequest.class);
		StringAndListParam param;
		
		ourLog.info("Pt1:{} Pt2:{} Obs1:{} Obs2:{} Obs3:{}", new Object[] {ptId1.getIdPart(), ptId2.getIdPart(), obsId1.getIdPart(), obsId2.getIdPart(), obsId3.getIdPart()});
		
		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientTypeEverything(request, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, devId1));

		request = mock(HttpServletRequest.class);
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientTypeEverything(request, null, null, null, null, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, obsId2, devId1, ptId2, obsId3));

		/*
		 * Add another match
		 */
		
		Observation obs4 = new Observation();
		obs4.getSubject().setReference(ptId1);
		obs4.getCode().addCoding().setCode("CODE1");
		obs4.setValue(new StringDt("obsvalue1"));
		IIdType obsId4 = myObservationDao.create(obs4).getId().toUnqualifiedVersionless();
		assertNotEquals(obsId1.getIdPart(), obsId4.getIdPart(), devId1);

		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientTypeEverything(request, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId1, obsId4, devId1));

		/*
		 * Make one previous match no longer match
		 */
		
		obs1 = new Observation();
		obs1.setId(obsId1);
		obs1.getSubject().setReference(ptId1);
		obs1.getCode().addCoding().setCode("CODE2");
		obs1.setValue(new StringDt("obsvalue2"));
		myObservationDao.update(obs1);

		param = new StringAndListParam();
		param.addAnd(new StringOrListParam().addOr(new StringParam("obsvalue1")));
		actual = toUnqualifiedVersionlessIds(myPatientDao.patientTypeEverything(request, null, null, null, param, null));
		assertThat(actual, containsInAnyOrder(ptId1, obsId4));

	}

	
	/**
	 * When processing transactions, we do two passes. Make sure we don't update the lucene index twice since that would
	 * be inefficient
	 */
	@Test
	public void testSearchDontReindexForUpdateWithIndexDisabled() {
		Patient patient;
		SearchParameterMap map;

		patient = new Patient();
		patient.getText().setDiv("<div>DIVAAA</div>");
		patient.addName().addGiven("NAMEAAA");
		IIdType pId1 = myPatientDao.create(patient).getId().toUnqualifiedVersionless();

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_TEXT, new StringParam("DIVAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		/*
		 * Update but don't reindex
		 */

		patient = new Patient();
		patient.setId(pId1);
		patient.getText().setDiv("<div>DIVBBB</div>");
		patient.addName().addGiven("NAMEBBB");
		myPatientDao.update(patient, null, false);

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));
		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), not(contains(pId1)));

		myPatientDao.update(patient, null, true);

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEAAA"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), empty());

		map = new SearchParameterMap();
		map.add(Patient.SP_NAME, new StringParam("NAMEBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_CONTENT, new StringParam("NAMEBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

		map = new SearchParameterMap();
		map.add(Constants.PARAM_TEXT, new StringParam("DIVBBB"));
		assertThat(toUnqualifiedVersionlessIds(myPatientDao.search(map)), contains(pId1));

	}

	@Test
	public void testSearchWithChainedParams() {
		String methodName = "testSearchWithChainedParams";
		IIdType pId1;
		{
			Patient patient = new Patient();
			patient.addName().addGiven("methodName");
			patient.addAddress().addLine("My fulltext address");
			pId1 = myPatientDao.create(patient).getId().toUnqualifiedVersionless();
		}

		Observation obs = new Observation();
		obs.getSubject().setReference(pId1);
		obs.setValue(new StringDt("This is the FULLtext of the observation"));
		IIdType oId1 = myObservationDao.create(obs).getId().toUnqualifiedVersionless();

		obs = new Observation();
		obs.getSubject().setReference(pId1);
		obs.setValue(new StringDt("Another fullText"));
		IIdType oId2 = myObservationDao.create(obs).getId().toUnqualifiedVersionless();

		List<IIdType> patients;
		SearchParameterMap params;

		params = new SearchParameterMap();
		params.add(Constants.PARAM_CONTENT, new StringParam("fulltext"));
		patients = toUnqualifiedVersionlessIds(myPatientDao.search(params));
		assertThat(patients, containsInAnyOrder(pId1));

		params = new SearchParameterMap();
		params.add(Constants.PARAM_CONTENT, new StringParam("FULLTEXT"));
		patients = toUnqualifiedVersionlessIds(myObservationDao.search(params));
		assertThat(patients, containsInAnyOrder(oId1, oId2));

	}

}
