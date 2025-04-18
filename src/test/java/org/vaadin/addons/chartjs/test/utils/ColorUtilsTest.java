package org.vaadin.addons.chartjs.test.utils;

import org.junit.Test;
import org.vaadin.addons.chartjs.utils.ColorUtils;

import static org.junit.Assert.assertEquals;

public class ColorUtilsTest {

  @Test
  public void toRgb() {
    int[] rgbArr = {54, 162, 235};
    assertEquals("rgb(54,162,235)", ColorUtils.toRgb(rgbArr));
  }

  @Test
  public void toRgba() {
    int[] rgbArr = {54, 162, 235};
    assertEquals("rgba(54,162,235,0.2)", ColorUtils.toRgba(rgbArr, 0.2));
  }

  @Test
  public void toRgbaAlphaInteger() {
    int[] rgbArr = {54, 162, 235};
    assertEquals("rgba(54,162,235,1.0)", ColorUtils.toRgba(rgbArr, 1));
  }

  @Test
  public void toRgbaAlphaOutOfRange() {
    int[] rgbArr = {54, 162, 235};
    assertEquals("rgba(54,162,235,1.0)", ColorUtils.toRgba(rgbArr, 10));
    assertEquals("rgba(54,162,235,0.0)", ColorUtils.toRgba(rgbArr, -10));
  }
}
