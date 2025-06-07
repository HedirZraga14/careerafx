<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\CoreExtension;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;
use Twig\TemplateWrapper;

/* offre/index.html.twig */
class __TwigTemplate_1a9da099210c26ddd7bd65c0aa558fe3 extends Template
{
    private Source $source;
    /**
     * @var array<string, Template>
     */
    private array $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->blocks = [
            'title' => [$this, 'block_title'],
            'body' => [$this, 'block_body'],
        ];
    }

    protected function doGetParent(array $context): bool|string|Template|TemplateWrapper
    {
        // line 1
        return "base.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "offre/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "offre/index.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "offre/index.html.twig", 1);
        yield from $this->parent->unwrap()->yield($context, array_merge($this->blocks, $blocks));
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

    }

    // line 3
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_title(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        yield "Liste des Offres";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 5
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_body(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        // line 6
        yield "<div class=\"row\">
    <div class=\"col-md-12\">
    </div>
    <div class=\"col-md-12\">
    </div>
</div>

<!-- Titre déplacé ici avec traduction -->
<h1 class=\"mt-5 mb-4 text-center\">";
        // line 14
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("list_of_offers"), "html", null, true);
        yield "</h1>

<!-- Recherche avec icône --> 
<div class=\"d-flex align-items-center justify-content-between mb-3\">
    <!-- Bouton \"Consulter état de candidature\" -->
    <a href=\"";
        // line 19
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_candidatureoffre");
        yield "\" class=\"btn btn btn-lg\" 
       style=\"background-color: rgb(255, 255, 255); /* Gris Bootstrap */
              color: rgb(50, 52, 53); 
              border-radius: 20px;
              margin-right: 20px;
              text-decoration: underline;\">";
        // line 24
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("check_application_status"), "html", null, true);
        yield "</a>

    <!-- Formulaire de recherche -->
    <form action=\"";
        // line 27
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_recherche");
        yield "\" method=\"GET\" class=\"d-flex align-items-center\">
        <input type=\"text\" name=\"search\" class=\"form-control form-control-lg\" 
               placeholder=\"";
        // line 29
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("search_offer"), "html", null, true);
        yield "\" 
               style=\"border-radius: 20px; padding-left: 20px; margin-right: 10px;\">
        <button type=\"submit\" class=\"btn btn-outline btn-lg\" 
                style=\"border-radius: 20px;\">
            <i class=\"fa fa-search\" style=\"color: rgb(115, 160, 141);\"></i>
        </button>
    </form>
</div>

<div class=\"container mt-5\">
    <div class=\"row\">
        ";
        // line 40
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["taboffre"]) || array_key_exists("taboffre", $context) ? $context["taboffre"] : (function () { throw new RuntimeError('Variable "taboffre" does not exist.', 40, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["offre"]) {
            // line 41
            yield "            <div class=\"col-md-4 mb-4\">
                <div class=\"card shadow-sm d-flex flex-column\">
                    ";
            // line 43
            if (CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "image", [], "any", false, false, false, 43)) {
                // line 44
                yield "                        <div class=\"d-flex justify-content-center\">
                            <img src=\"";
                // line 45
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/images/" . CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "image", [], "any", false, false, false, 45))), "html", null, true);
                yield "\" 
                                 class=\"rounded-circle\" 
                                 alt=\"Image de ";
                // line 47
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "nomposte", [], "any", false, false, false, 47), "html", null, true);
                yield "\" 
                                 style=\"width: 150px; height: 120px; object-fit: cover; 
                                        border: 3px solid white; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);\">
                        </div>
                    ";
            } else {
                // line 52
                yield "                        <div class=\"d-flex justify-content-center\">
                            <img src=\"";
                // line 53
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("uploads/default.png"), "html", null, true);
                yield "\" class=\"card-img-top rounded-circle\" alt=\"Image par défaut\" width=\"100\" height=\"100\">
                        </div>
                    ";
            }
            // line 56
            yield "                    <div class=\"card-body d-flex flex-column justify-content-between\">
                        <h5 class=\"card-title text-center\">";
            // line 57
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "nomposte", [], "any", false, false, false, 57), "html", null, true);
            yield "</h5>
                        <p class=\"card-text text-center\">
                            <strong>";
            // line 59
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("company"), "html", null, true);
            yield ":</strong> ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "entreprise", [], "any", false, false, false, 59), "html", null, true);
            yield "<br>
                            <strong>";
            // line 60
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("location"), "html", null, true);
            yield ":</strong> ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "localisation", [], "any", false, false, false, 60), "html", null, true);
            yield "<br>
                            <strong>";
            // line 61
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("salary"), "html", null, true);
            yield ":</strong> ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "salaire", [], "any", false, false, false, 61), "html", null, true);
            yield " DT<br>
                            <strong>";
            // line 62
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("type"), "html", null, true);
            yield ":</strong> ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "typeoffre", [], "any", false, false, false, 62), "nom", [], "any", false, false, false, 62), "html", null, true);
            yield " | ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "typecontrat", [], "any", false, false, false, 62), "nom", [], "any", false, false, false, 62), "html", null, true);
            yield "<br>
                        </p>

                        <div class=\"d-flex justify-content-center\">
                            <span class=\"badge ";
            // line 66
            yield ((CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "disponibilite", [], "any", false, false, false, 66)) ? ("bg-success") : ("bg-danger"));
            yield "\">
                                ";
            // line 67
            yield ((CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "disponibilite", [], "any", false, false, false, 67)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("Disponible"), "html", null, true)) : ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("Non disponible"), "html", null, true)));
            yield "
                            </span>
                        </div>
                        <div class=\"mt-3\">
                            <a href=\"";
            // line 71
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_postulerhedir", ["id" => CoreExtension::getAttribute($this->env, $this->source, $context["offre"], "id", [], "any", false, false, false, 71)]), "html", null, true);
            yield "\" class=\"btn btn-primary btn-sm w-100\">
                                ";
            // line 72
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("apply"), "html", null, true);
            yield "
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        ";
            $context['_iterated'] = true;
        }
        // line 79
        if (!$context['_iterated']) {
            // line 80
            yield "            <div class=\"col-12 text-center\">
                <p class=\"text-muted\">";
            // line 81
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans("no_offer_available"), "html", null, true);
            yield "</p>
            </div>
        ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['offre'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 84
        yield "       
    </div>
</div>
<div class=\"col-3 text-center\">
    ";
        // line 88
        if ((CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 88, $this->source); })()), "request", [], "any", false, false, false, 88), "locale", [], "any", false, false, false, 88) == "fr")) {
            // line 89
            yield "        <a href=\"";
            yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_offre", ["_locale" => "en"]);
            yield "\" class=\"btn btn\" style=\"border-radius: 20px; margin-right: 20px;\">
            <i class=\"fas fa-language\"></i> Traduire en Français
        </a>
    ";
        } else {
            // line 93
            yield "        <a href=\"";
            yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_offre", ["_locale" => "fr"]);
            yield "\" class=\"btn btn\" style=\"border-radius: 20px; margin-right: 20px;\">
            <i class=\"fas fa-language\"></i> Traduire en Anglais
        </a>
    ";
        }
        // line 97
        yield "</div>


";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    /**
     * @codeCoverageIgnore
     */
    public function getTemplateName(): string
    {
        return "offre/index.html.twig";
    }

    /**
     * @codeCoverageIgnore
     */
    public function isTraitable(): bool
    {
        return false;
    }

    /**
     * @codeCoverageIgnore
     */
    public function getDebugInfo(): array
    {
        return array (  289 => 97,  281 => 93,  273 => 89,  271 => 88,  265 => 84,  256 => 81,  253 => 80,  251 => 79,  239 => 72,  235 => 71,  228 => 67,  224 => 66,  213 => 62,  207 => 61,  201 => 60,  195 => 59,  190 => 57,  187 => 56,  181 => 53,  178 => 52,  170 => 47,  165 => 45,  162 => 44,  160 => 43,  156 => 41,  151 => 40,  137 => 29,  132 => 27,  126 => 24,  118 => 19,  110 => 14,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Liste des Offres{% endblock %}

{% block body %}
<div class=\"row\">
    <div class=\"col-md-12\">
    </div>
    <div class=\"col-md-12\">
    </div>
</div>

<!-- Titre déplacé ici avec traduction -->
<h1 class=\"mt-5 mb-4 text-center\">{{ 'list_of_offers'|trans }}</h1>

<!-- Recherche avec icône --> 
<div class=\"d-flex align-items-center justify-content-between mb-3\">
    <!-- Bouton \"Consulter état de candidature\" -->
    <a href=\"{{ path('app_candidatureoffre') }}\" class=\"btn btn btn-lg\" 
       style=\"background-color: rgb(255, 255, 255); /* Gris Bootstrap */
              color: rgb(50, 52, 53); 
              border-radius: 20px;
              margin-right: 20px;
              text-decoration: underline;\">{{ 'check_application_status'|trans }}</a>

    <!-- Formulaire de recherche -->
    <form action=\"{{ path('app_recherche') }}\" method=\"GET\" class=\"d-flex align-items-center\">
        <input type=\"text\" name=\"search\" class=\"form-control form-control-lg\" 
               placeholder=\"{{ 'search_offer'|trans }}\" 
               style=\"border-radius: 20px; padding-left: 20px; margin-right: 10px;\">
        <button type=\"submit\" class=\"btn btn-outline btn-lg\" 
                style=\"border-radius: 20px;\">
            <i class=\"fa fa-search\" style=\"color: rgb(115, 160, 141);\"></i>
        </button>
    </form>
</div>

<div class=\"container mt-5\">
    <div class=\"row\">
        {% for offre in taboffre %}
            <div class=\"col-md-4 mb-4\">
                <div class=\"card shadow-sm d-flex flex-column\">
                    {% if offre.image %}
                        <div class=\"d-flex justify-content-center\">
                            <img src=\"{{ asset('uploads/images/' ~ offre.image) }}\" 
                                 class=\"rounded-circle\" 
                                 alt=\"Image de {{ offre.nomposte }}\" 
                                 style=\"width: 150px; height: 120px; object-fit: cover; 
                                        border: 3px solid white; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);\">
                        </div>
                    {% else %}
                        <div class=\"d-flex justify-content-center\">
                            <img src=\"{{ asset('uploads/default.png') }}\" class=\"card-img-top rounded-circle\" alt=\"Image par défaut\" width=\"100\" height=\"100\">
                        </div>
                    {% endif %}
                    <div class=\"card-body d-flex flex-column justify-content-between\">
                        <h5 class=\"card-title text-center\">{{ offre.nomposte }}</h5>
                        <p class=\"card-text text-center\">
                            <strong>{{ 'company'|trans }}:</strong> {{ offre.entreprise }}<br>
                            <strong>{{ 'location'|trans }}:</strong> {{ offre.localisation }}<br>
                            <strong>{{ 'salary'|trans }}:</strong> {{ offre.salaire }} DT<br>
                            <strong>{{ 'type'|trans }}:</strong> {{ offre.typeoffre.nom }} | {{ offre.typecontrat.nom }}<br>
                        </p>

                        <div class=\"d-flex justify-content-center\">
                            <span class=\"badge {{ offre.disponibilite ? 'bg-success' : 'bg-danger' }}\">
                                {{ offre.disponibilite ? 'Disponible'|trans : 'Non disponible'|trans }}
                            </span>
                        </div>
                        <div class=\"mt-3\">
                            <a href=\"{{ path('app_postulerhedir', { id: offre.id }) }}\" class=\"btn btn-primary btn-sm w-100\">
                                {{ 'apply'|trans }}
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        {% else %}
            <div class=\"col-12 text-center\">
                <p class=\"text-muted\">{{ 'no_offer_available'|trans }}</p>
            </div>
        {% endfor %}
       
    </div>
</div>
<div class=\"col-3 text-center\">
    {% if app.request.locale == 'fr' %}
        <a href=\"{{ path('app_offre', {'_locale': 'en'}) }}\" class=\"btn btn\" style=\"border-radius: 20px; margin-right: 20px;\">
            <i class=\"fas fa-language\"></i> Traduire en Français
        </a>
    {% else %}
        <a href=\"{{ path('app_offre', {'_locale': 'fr'}) }}\" class=\"btn btn\" style=\"border-radius: 20px; margin-right: 20px;\">
            <i class=\"fas fa-language\"></i> Traduire en Anglais
        </a>
    {% endif %}
</div>


{% endblock %}
", "offre/index.html.twig", "C:\\careera\\templates\\offre\\index.html.twig");
    }
}
