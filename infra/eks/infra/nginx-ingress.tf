resource "kubernetes_manifest" "nginx_ingress_namespace" {
  manifest = yamldecode(file("${path.module}/../../k8s/nginx-ingress/namespace.yaml"))
}

resource "helm_release" "nginx_ingress" {
  name       = "nginx-ingress"
  namespace  = "ingress-nginx"
  chart      = "ingress-nginx"
  repository = "https://kubernetes.github.io/ingress-nginx"
  version    = "4.11.2"

  values = [file("${path.module}/../../k8s/nginx-ingress/values.yaml")]

  timeout = 600

  depends_on = [
    kubernetes_manifest.nginx_ingress_namespace,
    helm_release.metrics_server,
    helm_release.grafana,
    helm_release.kube-prometheus-stack
  ]
}
