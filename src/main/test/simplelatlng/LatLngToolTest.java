package simplelatlng;

import static org.junit.Assert.assertEquals;
import static simplelatlng.LatLngTool.distance;
import static simplelatlng.LatLngTool.distanceInRadians;
import static simplelatlng.LatLngTool.normalizeLatitude;
import static simplelatlng.LatLngTool.normalizeLongitude;

import org.junit.Test;

import simplelatlng.util.LatLngConfig;
import simplelatlng.util.LengthUnit;

public class LatLngToolTest {

	@Test
	public void testNormalizeLatitude() {
		double t = LatLngConfig.DEGREE_TOLERANCE;
		assertEquals(0, normalizeLatitude(0), t);
		assertEquals(-0.0, normalizeLatitude(-0.0), t);
		assertEquals(5.3, normalizeLatitude(5.3), t);
		assertEquals(-5.3, normalizeLatitude(-5.3), t);
		assertEquals(35.7838, normalizeLatitude(35.7838), t);
		assertEquals(-35.7838, normalizeLatitude(-35.7838), t);
		assertEquals(90, normalizeLatitude(90), t);
		assertEquals(-90, normalizeLatitude(-90), t);
		assertEquals(90, normalizeLatitude(105), t);
		assertEquals(-90, normalizeLatitude(-105), t);
		assertEquals(90, normalizeLatitude(5738), t);
		assertEquals(-90, normalizeLatitude(-5738), t);
		assertEquals(90, normalizeLatitude(Double.POSITIVE_INFINITY), t);
		assertEquals(-90, normalizeLatitude(Double.NEGATIVE_INFINITY), t);
		assertEquals(Double.NaN, normalizeLatitude(Double.NaN), t);
	}

	@Test
	public void testNormalizeLongitude() {
		double t = LatLngConfig.DEGREE_TOLERANCE;
		assertEquals(0, normalizeLongitude(0), t);
		assertEquals(-0.0, normalizeLongitude(-0.0), t);
		assertEquals(5.3, normalizeLongitude(5.3), t);
		assertEquals(-5.3, normalizeLongitude(-5.3), t);
		assertEquals(35.7838, normalizeLongitude(35.7838), t);
		assertEquals(-35.7838, normalizeLongitude(-35.7838), t);
		assertEquals(90, normalizeLongitude(90), t);
		assertEquals(-90, normalizeLongitude(-90), t);
		assertEquals(91.384, normalizeLongitude(91.384), t);
		assertEquals(-91.384, normalizeLongitude(-91.384), t);
		assertEquals(171.384, normalizeLongitude(171.384), t);
		assertEquals(-171.384, normalizeLongitude(-171.384), t);
		assertEquals(180, normalizeLongitude(180), t);
		assertEquals(-180, normalizeLongitude(-180), t);
		assertEquals(-170, normalizeLongitude(190), t);
		assertEquals(170, normalizeLongitude(-190), t);
		assertEquals(-146.516091, normalizeLongitude(213.483909), t);
		assertEquals(146.516091, normalizeLongitude(-213.483909), t);
		assertEquals(-170, normalizeLongitude(1990), t);
		assertEquals(170, normalizeLongitude(-1990), t);
		assertEquals(Double.NaN, normalizeLongitude(Double.POSITIVE_INFINITY), t);
		assertEquals(Double.NaN, normalizeLongitude(Double.NEGATIVE_INFINITY), t);
		assertEquals(Double.NaN, normalizeLongitude(Double.NaN), t);
	}

	@Test
	public void testDistance() {
		double t = 0.01; // Distance tolerance is less picky.
		assertEquals(0, distance(new LatLng(0, 0), new LatLng(0, 0),
				LengthUnit.MILE), t);
		assertEquals(0, distance(new LatLng(36.79283, -127.36629), new LatLng(
				36.79283, -127.36629), LengthUnit.MILE), t);

		assertEquals(111.19, distance(new LatLng(0, 0), new LatLng(0, 1),
				LengthUnit.KILOMETER), t);
		assertEquals(111.19, distance(new LatLng(0, 0), new LatLng(1, 0),
				LengthUnit.KILOMETER), t);
		assertEquals(111.19, distance(new LatLng(0, 0), new LatLng(0, -1),
				LengthUnit.KILOMETER), t);
		assertEquals(111.19, distance(new LatLng(0, 0), new LatLng(-1, 0),
				LengthUnit.KILOMETER), t);
		assertEquals(111.19, distance(new LatLng(-1, 0), new LatLng(0, 0),
				LengthUnit.KILOMETER), t);

		assertEquals(5133.65, distance(new LatLng(-67.5, 45), new LatLng(0, 0),
				LengthUnit.MILE), t);
		assertEquals(8261.81, distance(new LatLng(-67.5, 45), new LatLng(0, 0),
				LengthUnit.KILOMETER), t);
	}

	@Test
	public void testDistanceInRadians() {
		distRadTest(1, new LatLng(0, 0), new LatLng(0, 1));
		distRadTest(1, new LatLng(0, 0), new LatLng(1, 0));
		distRadTest(45, new LatLng(0, 0), new LatLng(0, 45));
		distRadTest(45, new LatLng(0, 0), new LatLng(45, 0));
	}

	@Test
	public void distanceToDegrees() {
		// TODO: unit tests for this.
	}

	private static void distRadTest(double expected, LatLng point1, LatLng point2) {
		assertEquals(expected, Math.toDegrees(distanceInRadians(point1, point2)),
				.00001);
	}
}