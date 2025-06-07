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

/* security/profilemoderateur.html.twig */
class __TwigTemplate_883f30003b8e5119c23b4601550069bf extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "security/profilemoderateur.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "security/profilemoderateur.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "security/profilemoderateur.html.twig", 1);
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

        yield "Profil du RH / Recruteur";
        
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
        yield "<div class=\"container mt-5\">
    <div class=\"card shadow-lg p-4\">
        <div class=\"row\">
            <div class=\"col-md-4 text-center\">
                
            </div>

            <div class=\"col-md-8\">
                ";
        // line 14
        if ((array_key_exists("tabprofile", $context) &&  !(null === (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 14, $this->source); })())))) {
            // line 15
            yield "                    <h2 class=\"text-primary\">
                        ";
            // line 16
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "nom", [], "any", true, true, false, 16)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 16, $this->source); })()), "nom", [], "any", false, false, false, 16), "html", null, true)) : ("Nom indisponible"));
            yield "
                        ";
            // line 17
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "prenom", [], "any", true, true, false, 17)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 17, $this->source); })()), "prenom", [], "any", false, false, false, 17), "html", null, true)) : ("Prénom indisponible"));
            yield "
                    </h2>
                    <p><strong>Email :</strong> ";
            // line 19
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "email", [], "any", true, true, false, 19)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 19, $this->source); })()), "email", [], "any", false, false, false, 19), "html", null, true)) : ("Email indisponible"));
            yield "</p>
                    <p><strong>Téléphone :</strong> ";
            // line 20
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "tel", [], "any", true, true, false, 20)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 20, $this->source); })()), "tel", [], "any", false, false, false, 20), "html", null, true)) : ("Téléphone indisponible"));
            yield "</p>
                    <p><strong>Domaine :</strong> ";
            // line 21
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "domaine", [], "any", true, true, false, 21)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 21, $this->source); })()), "domaine", [], "any", false, false, false, 21), "html", null, true)) : ("Domaine indisponible"));
            yield "</p>
                    <p><strong>Société :</strong> ";
            // line 22
            yield ((CoreExtension::getAttribute($this->env, $this->source, ($context["tabprofile"] ?? null), "societe", [], "any", true, true, false, 22)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 22, $this->source); })()), "societe", [], "any", false, false, false, 22), "html", null, true)) : ("Société indisponible"));
            yield "</p>

            
                    <!-- Bouton Modifier -->
                    <a href=\"";
            // line 26
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_updatemoderateur", ["id" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 26, $this->source); })()), "id", [], "any", false, false, false, 26)]), "html", null, true);
            yield "\" class=\"btn btn-success mt-3\">
                        Modifier
                    </a>

                    <!-- Bouton Supprimer -->
                    <a href=\"";
            // line 31
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_deletemoderateur", ["id" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["tabprofile"]) || array_key_exists("tabprofile", $context) ? $context["tabprofile"] : (function () { throw new RuntimeError('Variable "tabprofile" does not exist.', 31, $this->source); })()), "id", [], "any", false, false, false, 31)]), "html", null, true);
            yield "\" class=\"btn btn-danger mt-3\" onclick=\"return confirm('Êtes-vous sûr de vouloir supprimer ce profil ?')\">
                        Supprimer
                    </a>
                ";
        } else {
            // line 35
            yield "                    <p>Aucun profil trouvé.</p>
                ";
        }
        // line 37
        yield "            </div>
        </div>
    </div>
</div>
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
        return "security/profilemoderateur.html.twig";
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
        return array (  162 => 37,  158 => 35,  151 => 31,  143 => 26,  136 => 22,  132 => 21,  128 => 20,  124 => 19,  119 => 17,  115 => 16,  112 => 15,  110 => 14,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Profil du RH / Recruteur{% endblock %}

{% block body %}
<div class=\"container mt-5\">
    <div class=\"card shadow-lg p-4\">
        <div class=\"row\">
            <div class=\"col-md-4 text-center\">
                
            </div>

            <div class=\"col-md-8\">
                {% if tabprofile is defined and tabprofile is not null %}
                    <h2 class=\"text-primary\">
                        {{ tabprofile.nom is defined ? tabprofile.nom : 'Nom indisponible' }}
                        {{ tabprofile.prenom is defined ? tabprofile.prenom : 'Prénom indisponible' }}
                    </h2>
                    <p><strong>Email :</strong> {{ tabprofile.email is defined ? tabprofile.email : 'Email indisponible' }}</p>
                    <p><strong>Téléphone :</strong> {{ tabprofile.tel is defined ? tabprofile.tel : 'Téléphone indisponible' }}</p>
                    <p><strong>Domaine :</strong> {{ tabprofile.domaine is defined ? tabprofile.domaine : 'Domaine indisponible' }}</p>
                    <p><strong>Société :</strong> {{ tabprofile.societe is defined ? tabprofile.societe : 'Société indisponible' }}</p>

            
                    <!-- Bouton Modifier -->
                    <a href=\"{{ path('app_updatemoderateur', {'id': tabprofile.id}) }}\" class=\"btn btn-success mt-3\">
                        Modifier
                    </a>

                    <!-- Bouton Supprimer -->
                    <a href=\"{{ path('app_deletemoderateur', {'id': tabprofile.id}) }}\" class=\"btn btn-danger mt-3\" onclick=\"return confirm('Êtes-vous sûr de vouloir supprimer ce profil ?')\">
                        Supprimer
                    </a>
                {% else %}
                    <p>Aucun profil trouvé.</p>
                {% endif %}
            </div>
        </div>
    </div>
</div>
{% endblock %}", "security/profilemoderateur.html.twig", "C:\\careera\\templates\\security\\profilemoderateur.html.twig");
    }
}
