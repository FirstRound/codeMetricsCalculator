class MetricAgregator {

  public static void main(String[] args) {
    IMetric im = new AHFMetric();
    im.calculateMetric("Hello, world!");
  }

}
